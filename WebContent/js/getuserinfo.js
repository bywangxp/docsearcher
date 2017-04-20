$(function(){
	//用户栏信息异步加载
	$.ajax({
		url:'/DocSearcher/user/part',
		type:'POST',
		data:'',
		datatype:'json',
		success:function(rtext){
			var $name=$('.left').find('p.name');
			var $credit=$name.next().children('span:first-child');
			var $lvl=$name.next().children('span:last-child');
			var $asset=$('.asset-info').find('p.asset');
			var $doc=$asset.eq(0);
			var $rate=$asset.eq(1);
			var $collect=$asset.eq(2);
			console.log(rtext);
			$('#pic').attr('src',rtext.img);
			$name.text(rtext.nickName);
			console.log(rtext.nickName);
			var name=$name.text();
			console.log(name);
			$credit.text(rtext.credit);
			$lvl.text(rtext.level);
			$asset.text(rtext.collectnum);
			$doc.text(rtext.docnum);
			$rate.text(rtext.ratenum);
			$('.signiture textarea').text(rtext.description);
		},
		error:function(XMLHttpRequest,textStatus,errorThrown){
	 				console.log(XMLHttpRequest.status);
	 				console.log(XMLHttpRequest.readyState);
	 				console.log(textStatus);
	 			}
	});

});