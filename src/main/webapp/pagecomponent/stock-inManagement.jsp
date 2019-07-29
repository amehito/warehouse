<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
	<style type="text/css">
		table {
				width: 100%;
			}
			
			input {
				width: 100%;
			}
			.supplier{
				width:100%;
				height: 100px;
				position: absolute;
				display: block;
			}
		}
   
		</style>
	</head>

	<body>
		
	<div class="panel panel-default">
    	<div class="row" id="supplier">
    	   <div class="col-md-1" >
            <h5>供应商：</h5> 
         </div>
  	     <div class="col-md-1">
           <button class="btn ">新益</button> 
         </div>
                    <button class="btn ">益</button> 
         
    	</div>
    	
    	<div class="row" id="manufacturer">
    		<div class="col-md-1" >
          	  <h5>厂商：</h5> 
          	</div>     
	  	     <div class="col-md-1" >
	           <button class="btn ">新益</button> 
	         </div>
        	      
        </div>
    
			<form action="">
				<table class="table table-bordered">
					<thead>
						<tr>
							<td><strong>机料物代码</strong></td>
							<td>亚伦代码</td>
							<td><strong>机料物名</strong></td>
							<td>物料种类</td>
							<td>仓库位置</td>
							<td><strong>入库数量</strong></td>
							<td><strong>含税价</strong></td>
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
							<td><input type="" name=""  id="auto" oninput="searchForFill(this.value)"></td>
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
			<button class="btn btn-info" onclick="addTable()">添加</button>
		</div>
		<script>
		const tableWidth = 18;
		let batchManage = new Date().toString().split(' ').join('').slice(0,20);
			let fill = `<tr>
		      <td><input type='text'  oninput="searchForFill(this.value)"></td>
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

     let allMaterial;
     fetch('Material/getMaterialInfo',{
      headers:new Headers({
             "Content-Type":"application/json",
      }),
      method:'get',
    })
    .then(response => response.json())
    .then(data=>{
    	allMaterial = data
    	
    });
 		 
		function submitData() {
			let materialData = [];
			materialData = eval(materialData);
			let inputs = document.querySelectorAll('table input');
			for(let i=0;i<inputs.length/tableWidth;i++){
        if(inputs[i*tableWidth+0].value ==='' || inputs[i*tableWidth+5].value==='' ||inputs[i*tableWidth+6].value==='' ){
          continue;
        }
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
				async:false,
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
    	
    	  let item=inputs.length/tableWidth-1;	
    	//	  	inputs[i*tableWidth+0].addEventListener('blur',addTable);
  			inputs[item*tableWidth+8].value=13;	
  	        inputs[item*tableWidth+8].addEventListener('input',fillMoneyInput);
  	        inputs[item*tableWidth+6].addEventListener('input',fillMoneyInput);
  	        inputs[item*tableWidth+5].addEventListener('input',fillMoneyInput); 
	  	    
    	  
        //添加事件监听
 
      }
	  
    /* allMaterial.forEach(function(item){
      arr.push(item.materialId);
    }) */
      

      function searchForFill(id){
    	  //给所有input框添加id
    	  
    	let arr = [];
    	/* allMaterial.filter(item => return item.materialId.search(id)!== -1).forEach(item => {
    		arr.push(item.materialId);
    	});
		console.log({arr}); */
    	let result = allMaterial.filter(item => item.materialId.search(id)!== -1).slice(0,100);
        arr = [];
        arr.push(id);
        result.forEach(item => {arr.push(item.materialId)});
        console.log({arr})
        let allArray = document.querySelectorAll('table input');
    	let index = allArray.length-tableWidth;
    	let inputItem = allArray[index];

    	$(inputItem).autocomplete({
      	  source:arr,
    	  select:function(event,ui){
    		  console.log(ui.item.label);
    		  fillByMaterialId(index,ui.item.label,result);
    		  return false;
    	  },
    	  focus:function(event,ui){
    	 	 let info = result.filter(item => item.materialId == ui.item.label);
        	  allArray[index+2].value = info[0].materialName;
    		  return false;
    	  }
    	  });

      }
      function fillByMaterialId(row,materialId,result){
    	  console.log({row,materialId,result});
    	  let info = result.filter(item => item.materialId == materialId);
    	  //填充info
    	  console.log({info});
    	  let inputArrays = document.querySelectorAll('table input');
    	  inputArrays[row+0].value = info[0].materialId;
    	  inputArrays[row+1].value = info[0].viceId;
    	  inputArrays[row+2].value = info[0].materialName;
        inputArrays[row+3].value = info[0].materialSpec;
        inputArrays[row+4].value = info[0].warehousePosition;
        inputArrays[row+9].value = info[0].materialType;
        inputArrays[row+13].value = info[0].materialUnit;
        
      }
      function fillMoneyInput(){
    	  let inputs = document.querySelectorAll('table input');
    	  for(let i=0;i<inputs.length/tableWidth;i++){ 
    		let number = parseInt(inputs[i*tableWidth+5].value);
    		let taxRate =parseFloat( inputs[i*tableWidth+8].value);
            let priceIncludeTax = parseFloat(inputs[i*tableWidth+6].value);
            let price = priceIncludeTax*100/(100+taxRate);
            console.log({number,taxRate,priceIncludeTax});
            inputs[i*tableWidth+7].value = price;
            inputs[i*tableWidth+10].value = number *(priceIncludeTax - price);
           	inputs[i*tableWidth+11].value = number * price;
            inputs[i*tableWidth+12].value = number * priceIncludeTax;		
    	  }
      }
		function pageInit(){
			let father = document.querySelector('#supplier');
			let father2 = document.querySelector('#manufacturer');
			father.onclick=function(ev){
				let allInput = document.querySelectorAll('table input');
				for(let i=0;i<allInput.length/tableWidth;i++){
					allInput[i*tableWidth+16].value = ev.target.innerText;
					allInput[i*tableWidth+14].value = batchManage;
				}
			}
			father2.onclick=function(ev){
				let allInput = document.querySelectorAll('table input');
				for(let i=0;i<allInput.length/tableWidth;i++){
					allInput[i*tableWidth+15].value = ev.target.innerText;
					allInput[i*tableWidth+14].value = batchManage;

				}
				console.log(batchManage);
			}
			

		}
		
      
		window.onload=pageInit();

    	
      
		</script>
	
 

	</body>

</html>