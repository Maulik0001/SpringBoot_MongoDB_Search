<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet"> 
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<!-- CSS -->
	<style>
	Table.GridOne 
		{
		 padding: 3px;
		 margin: 0;
		 background: lightyellow;
		 border-collapse: collapse; 
		 width:35%;
		}
	Table.GridOne Td 
		{ 
		 padding:2px;
		 border: 1px solid #ff9900;
		 border-collapse: collapse;
		} 
	</style>
	<script type="text/javascript">
	
	
	jQuery(document).ready(function() {
		$("#displayTable").hide();
	});
	
	function searchName() {
		var firstname = $("#search").val();
		$.ajax({
			type : "GET",
			url : "searchName",
			dataType : "json",
			data : {
				firstname : firstname
			},
			success : function(data) {
				var rows = '';
				$.each(data, function(index, item) {
					rows += '<tr><td>' + item.id + '</td>';
					rows += '<td>' + item.firstname + '</td>';
					rows += '<td>' + item.lastname + '</td>';
					rows += '<td>' + item.gender + '</td>';
					rows += '<td>' + item.address + '</td>';
					rows += '<td onclick="editAjaxData(' + item.id
							+ ');" >edit</td>';
					rows += '<td onclick="deleteAjaxData(' + item.id
							+ ');" >Delete</td></tr>';

				});
				$('#tblProducts').html(rows);
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				alert("error");
			}
		});
	}
	
	function searchClick() {
		var searchText = $("#searchtext").val();
		$.ajax({
			type : "GET",
			url : "searchByValue",
			dataType : "json",
			data : {
				searchText : searchText
			},
			success : function(data) {
				var rows = '';
				$.each(data, function(index, item) {
					rows += '<tr><td>' + (index + 1) + '</td>';
					rows += '<td>' + item.syllabusYear + '</td>';
					 if (item.fileObjects.length > 0) {
						$.each(item.fileObjects, function(oIndex, oItem) {
						if(oIndex==0) {
							rows += '<td>' + (oIndex+1) + '</td>';//object_name,count_word
							rows += '<td>'+ oItem.objectName + '</td>';
							if (oItem.uniqueWords.length > 0) {
								$.each(oItem.uniqueWords, function(uIndex,uItem) {
									if(uIndex==0) {
										rows += '<td>' + uItem.uniqueWord+ '</td>';
										rows += '<td>' + uItem.uniqueWordLanguage+ '</td>';
										rows += '<td>' + uItem.unqueWordCount+ '</td></tr>';
									} else {
										rows += '<td></td><td></td><td></td><td></td><td>' + uItem.uniqueWord+ '</td>';
										rows += '<td>' + uItem.uniqueWordLanguage+ '</td>';
										rows += '<td>' + uItem.unqueWordCount+ '</td></tr>';
									}
								});
							}
						} else {
							rows += '<td></td><td></td>';
							rows += '<td>' +  (oIndex+1)  + '</td>';//object_name,count_word
							rows += '<td>'+ oItem.objectName + '</td>';
							if (oItem.uniqueWords.length > 0) {
								$.each(oItem.uniqueWords, function(uIndex,uItem) {
									if(uIndex==0) {
										rows += '<td>' + uItem.uniqueWord+ '</td>';
										rows += '<td>' + uItem.uniqueWordLanguage+ '</td>';
										rows += '<td>' + uItem.unqueWordCount+ '</td></tr>';
									} else {
										rows += '<td></td><td></td><td></td><td></td><td>' + uItem.uniqueWord+ '</td>';
										rows += '<td>' + uItem.uniqueWordLanguage+ '</td>';
										rows += '<td>' + uItem.unqueWordCount+ '</td></tr>';
									}
								});
							}
						}
						});
					}
				});
				if(data.length>0) {
					$("#tblProducts").html(rows);
					$("#displayTable").show();
				} else {
					rows = '<tr><td colspan="7">Data not available</td></tr>';
					$("#tblProducts").html(rows);
					$("#displayTable").show();
				}
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				alert("error");
			}
		});
	}
		
	</script>
</head>
<body>
	<center>
	<div>
	<h2 align="center">Search page</h2>
	
	<input type="text" name="search" id="searchtext">
	
	<input type="button"  value="Search" id="btnSubmit" onclick="searchClick()"/>
	</div>
	
	<div>
	<br>
	<br>
	<table id="displayTable"  border="1">
	  <thead >
	  	<tr>
		  <th> id </th>
		  <th> syllabus year </th>
		  <th> file no  </th>
		  <th> file objects name </th>
		  <th> unique word </th>
		  <th> word language </th>
		  <th> word count </th>
		</tr>
	  </thead>
	  <tbody id="tblProducts" >
	  
	  </tbody>
	</table>
	
	</div>
	
	
	</center>
</body>
</html>