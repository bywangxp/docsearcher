$(function(){
	var totalP=null;
	var page=0;
	var Id=null;
	var curPage=null;
	alert('js1');

	$.ajax({
	url:'/DocSearcher/user/contri_data',
	type:'POST',
	data:page,
	dataType:'json',
	success:function(json){
      totalP=json.totalpage;
      var oRow=Json.parse(json.tablerow);
      for(var i=0;i<oRow.length;i++){
      Id.push(oRow[i].id);}
	},
	error:function(XMLHttpRequest,textStatus,errorThrown){
						console.log(XMLHttpRequest.status);
	 					console.log(XMLHttpRequest.readyState);
	 					console.log(textStatus);
			      	}
	});

	$('#page').page({
		leng:12,
		activeClass:'activP',
	});
	$('#page').find('ul').attr('class','pagingUl');
	// $('#page').find('li').css({'float','left'},{'list-style','none'},{'display','inline'});

});