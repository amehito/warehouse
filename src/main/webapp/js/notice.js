const ms = 5000;

window.onload = function(){
	//setInterval(receive,ms);
	setInterval(receive,ms);
}

function showRequestDetails(text){
//	console.log({text,"122"});
	console.log(123);
}
function showNotification(text){
	
	document.getElementById('notifyPanel').innerHTML += `
		<div class="panel panel-default" >
			<div class="panel-body">
				有一条来自${text.admin}的取料请求，点击我查看
				
  			</div>
		</div>
		`;
	
	console.log(text);
//	document.body.appendChild(notifyDiv);

}
let receive = function(){
	$.ajax({
		url:'Material/showNotification',
		data:'notice',
		type:'POST',
		success:function(data){
			console.log(data);
			let text = data;
			showNotification(text);
		}
	});
}