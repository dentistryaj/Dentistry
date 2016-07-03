<html>
<head>
<title>Upload File Request Page</title>
</head>
<body>
	<form method="POST" action="/Dentistry/topic/create" enctype="multipart/form-data">

		<table>
			<tr>
			<td>Topic Name : </td>
			<td><input type="text" name="topicName"></td>
			</tr>
		
			<tr>
			<td>File to upload: </td>
			<td><input type="file" name="file">
		Name: <input type="text" name="name">
			</td>
			</tr>
			
				<tr>
			<td>Topic Description :  </td>
			<td><textarea name="description" rows="30" cols="140" ></textarea></td>
			</tr>
			
				<tr>
			<td>ParentId :  </td>
			<td><input type="text" name="parentId"><br/><br /></td>
			</tr>
		
			<tr>
			<td></td>
			<td><input type="submit" value="Upload"> Press here to upload the file!</td>
			</tr>
		
		
		
		
		</table>
		
		<br /><br />	

		
		
		
  		
 
 
		
	</form>	
</body>
</html>


</body>
</html>