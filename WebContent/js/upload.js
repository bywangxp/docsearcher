$(document).ready(function(e){
	
	alert("haha");
	$('#f-sec').css('display','block');
    $('#s-sec').css('display','none');
    $('#l-sec').css('display','none');

 $('body').on('change',$('#upload input'),function(){
	 if($('#upload input').val()!=''){
 	console.log($('#DocInput').val());

	$('#f-sec').css('display','none');
    $('#s-sec').css('display','block');
    $('#l-sec').css('display','none');
    
	 
 var Ival=$('#upload input').val();
 console.log(Ival);
 var idot=Ival.indexOf('.');
 console.log(idot);
 var ai=Ival.lastIndexOf('\\');
 console.log(ai);
 var title=Ival.substring(ai+1,idot);
 console.log(title);
  $('#ti input').val(title);





}
});

});

	
