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
					<tbody id="tbody">
						
					</tbody>
					<tbody>
						<tr>
							<td><input type="" name="" onFocus="addTable(this)"></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name=""></td>
							<td><input type="" name="" value="13"></td>
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
			<button class="btn btn-primary">提交</button>
		</div>
		<script>
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

			function addTable(i) {
				document.querySelector('tbody').innerHTML += fill;
        fillInit();
				i.blur();
			}
      function fillInit(){
        
      }
			
		</script>
	</body>

<ml>