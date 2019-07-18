const ms = 1000;
window.onload = function(){
	//setInterval(receive,ms);
	setInterval(receive,ms);
}
function showNotification(text){
	
	document.getElementById('notifyPanel').innerHTML = `
		<div class="panel panel-default">
			<div class="panel-body">
				${text}
  			</div>
		</div>
		`;
	
	console.log('提示');
//	document.body.appendChild(notifyDiv);

}
let receive = function(){
	$.ajax({
		url:'Material/notify',
		data:'notice',
		type:'POST',
		success:function(data){
			console.log(data);
			let text = data;
			showNotification(text);
		}
	});
}