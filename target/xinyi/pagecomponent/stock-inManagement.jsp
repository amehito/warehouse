<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
	</head>

	<body>
		<style type="text/css">
		table {
				width: 100%;
			}
			
			input {
				width: 100%;
			}
		}
		</style>
		<div class="panel panel-default">
			<form action="">
				<table class="table table-bordered">
					<thead>
						<tr>
							<td>机料物代码</td>
							<td>亚伦代码</td>
							<td>机料物名</td>
							<td>物料种类</td>
							<td>仓库位置</td>
							<td>入库数量</td>
							<td>含税价</td>
							<td>单价</td>
							<td>税率</td>
							<td>规格</td>
							<td>税金</td>
							<td>不含税金</td>
							<td>含税金额</td>
							<td>单位</td>
							<td>批次号</td>
							<td>供应商</td>
							<td>厂商</td>
							<td>备注</td>
						</tr>
					</thead>
					
					<tbody id='tbody'>
						<tr>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name="" oninput="fillMoneyInput()"></td>
							<td><input type="" name="" oninput="fillMoneyInput()"></td>
							<td><input type="" name=""></td>
							<td><input type="" name="" value="13" oninput="fillMoneyInput()"></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
						</tr>

					</tbody>

				</table>
			</form>
			<button class="btn btn-primary" onclick="submitData()">提交</button>
			<button class="btn btn-default" onclick="addTable()">添加</button>
		</div>
		<script>
		const tableWidth = 18;
			let fill = `<tr>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
		      <td><input type='text'></td>
 		 </tr>`;
 		 
		function submitData() {
			let materialData = [];
			materialData = eval(materialData);
			let inputs = document.querySelectorAll('table input');
			for(let i=0;i<inputs.length/tableWidth;i++){
				let item = {
			materialId:inputs[i*tableWidth+0].value,
			viceId:inputs[i*tableWidth+1].value,
            materialName:inputs[i*tableWidth+2].value,
            materialSpec:inputs[i*tableWidth+3].value,
            warehousePosition:inputs[i*tableWidth+4].value,
            importNumber:inputs[i*tableWidth+5].value,
            priceIncludeTax:inputs[i*tableWidth+6].value,
            price:inputs[i*tableWidth+7].value,
            taxRate:inputs[i*tableWidth+8].value,
            size:inputs[i*tableWidth+9].value,
            tax:inputs[i*tableWidth+10].value,
            totalPriceIncludeTax:inputs[i*tableWidth+11].value,
            totalPrice:inputs[i*tableWidth+12].value,
            unit:inputs[i*tableWidth+13].value,
            batchManage:inputs[i*tableWidth+14].value,
            supplier:inputs[i*tableWidth+15].value,
            manufacturing:inputs[i*tableWidth+16].value,
            plus:inputs[i*tableWidth+17].value,
						
				}
				materialData.push(item);
				console.log(materialData);
			}
			$.ajax({
				url:'Material/importMaterials',
				type:'POST',
				data:JSON.stringify(materialData),
				dataType:'json',
				contentType:"application/json",
				success:function(){
					
				}
			})
		}
			function addTable() {
				let childNode = document.createElement('tr');
				childNode.innerHTML = fill;
				document.querySelector('tbody').appendChild(childNode);
			  	fillInit();

			}
      function fillInit(){ 
    	  let inputs = document.querySelectorAll('table input');
    	  for(let i=0;i<inputs.length/tableWidth;i++){   	
    	//	  	inputs[i*tableWidth+0].addEventListener('blur',addTable);
    			inputs[i*tableWidth+8].value=13;	
    	        inputs[i*tableWidth+8].addEventListener('input',fillMoneyInput);
    	        inputs[i*tableWidth+6].addEventListener('input',fillMoneyInput);
    	        inputs[i*tableWidth+5].addEventListener('input',fillMoneyInput);
	
    	  }
        //添加事件监听
 
      }
      function fillMoneyInput(){
    	  let inputs = document.querySelectorAll('table input');
    	  for(let i=0;i<inputs.length/tableWidth;i++){ 
    		let number = parseInt(inputs[i*tableWidth+5].value);
    		let taxRate =parseFloat( inputs[i*tableWidth+8].value);
            let priceIncludeTax = parseFloat(inputs[i*tableWidth+6].value);
            let price = priceIncludeTax*100/(100+taxRate);
            console.log({number,taxRate,priceIncludeTax});
            console.log(100+taxRate);
            inputs[i*tableWidth+7].value = price;
            inputs[i*tableWidth+10].value = number *(priceIncludeTax - price);
           	inputs[i*tableWidth+11].value = number * price;
            inputs[i*tableWidth+12].value = number * priceIncludeTax;		
            console.log({taxRate});
    	  }
      }
		</script>
	</body>

</html>