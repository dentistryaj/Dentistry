package com.dentistry.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dentistry.model.Topic;
import com.dentistry.services.TopicServices;

@Controller
@RequestMapping("/topic")
public class TopicController {

	@Autowired
	TopicServices dataServices;

	@Autowired
	ServletContext context;

	static final Logger logger = Logger.getLogger(TopicController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String addTopic(@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("topicName") String topicName,
			@RequestParam("parentId") Long parentId,
			@RequestParam("file") MultipartFile file) {

		try {
			if (!file.isEmpty()) {

				byte[] bytes = file.getBytes();

				// Creating the directory to store file

				String uploadPath = context.getRealPath("") + File.separator
						+ "/WEB-INF/images";
				File dir = new File(uploadPath);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);

				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				saveGridImage(serverFile);

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

			} 
			Topic topic = new Topic();
			topic.setTopicName(topicName);
			if(!"".equalsIgnoreCase(name))
				topic.setTopicLink(name);
			topic.setTopicDesc(description);
			if (parentId != null)
				topic.setParentTopic(getTopic(parentId));

			try {
				boolean success = dataServices.addEntity(topic);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return "You successfully uploaded file=" + name;

		} catch (Exception e) {
			return "You failed to upload " + name + " => " + e.getMessage();
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Topic getTopic(@PathVariable("id") long id) {
		Topic topic = null;
		try {
			topic = dataServices.getEntityById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return topic;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Topic> getAllTopic() {
		Topic topic = null;
		try {
			List<Topic> topicList = dataServices.getEntityList();
			return topicList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void saveGridImage(File output) throws IOException {
		// output.delete();

		final String formatName = "jpeg";

		ImageReader reader = ImageIO.getImageReadersByFormatName("jpeg").next();
		reader.setInput(new FileImageInputStream(output), true, false);
		IIOMetadata data = reader.getImageMetadata(0);
		BufferedImage image = reader.read(0);

		for (Iterator<ImageWriter> iw = ImageIO
				.getImageWritersByFormatName(formatName); iw.hasNext();) {
			ImageWriter writer = iw.next();
			ImageWriteParam writeParam = writer.getDefaultWriteParam();
			ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier
					.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
			IIOMetadata metadata = writer.getDefaultImageMetadata(
					typeSpecifier, writeParam);
			if (metadata.isReadOnly()
					|| !metadata.isStandardMetadataFormatSupported()) {
				continue;
			}

			setDPI(metadata);

			final ImageOutputStream stream = ImageIO
					.createImageOutputStream(output);
			try {
				writer.setOutput(stream);
				writer.write(metadata, new IIOImage(image, null, metadata),
						writeParam);
			} finally {
				stream.close();
			}
			break;
		}
	}

	private void setDPI(IIOMetadata metadata) throws IIOInvalidTreeException {

		// for PMG, it's dots per millimeter
		double dotsPerMilli = 1.0 * 72 / 10 / 2.54;

		IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
		horiz.setAttribute("value", Double.toString(dotsPerMilli));

		IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
		vert.setAttribute("value", Double.toString(dotsPerMilli));

		IIOMetadataNode dim = new IIOMetadataNode("Dimension");
		dim.appendChild(horiz);
		dim.appendChild(vert);

		IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
		root.appendChild(dim);

		metadata.mergeTree("javax_imageio_1.0", root);
	}

}
