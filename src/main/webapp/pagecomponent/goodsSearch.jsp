<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    
 <script>
    // 鏌ヨ鍙傛暟
    search_user_id = null
    search_start_date = null
    search_end_date = null

    $(function(){
        datePickerInit();
        userOperationRecordTableInit();
        searchActionInit();
    })

    // 鏃ユ湡閫夋嫨鍣ㄥ垵濮嬪寲
	function datePickerInit(){
		$('.form_date').datetimepicker({
			format:'yyyy-mm-dd',
			language : 'zh-CN',
			endDate : new Date(),
			weekStart : 1,
			todayBtn : 1,
			autoClose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			minView:2
		});
	}

	// 琛ㄦ牸鍒濆鍖�
	function userOperationRecordTableInit(){
		$('#userOperationRecordTable').bootstrapTable({
			columns:[{
	            field : 'materialId',
	            title : '零件编号'
	        },{
	            field : 'createManager',
	            title : '管理员编号'
	        },{
	            field : 'userName',
	            title : '管理员姓名'
	        },{
	            field : 'materialName',
	            title : '零件名'
	        },{
	            field : 'startTime',
	            title : '时间'
	        },{
	            field : 'stockNumber',
	            title : '剩余数量',
	           	formatter:function(value,row,index){
	           		console.log({value,row,index});
	           		var s = `<button class="btn btn-success" type="button">
	           				${"${value}"}
	          			 	 </button>`;
	          		console.log(row.stockSafe);
	           		if(value>=row.stockSafe)
		           		return s;
	           		var t =  `<button class="btn btn-danger" type="button">
           					${"${value}"}
         			 	 </button>`;
	           		return t;
	           			
	           		
	           	},events:{
	           		
	           	}
	        }],
	        url:'Material/getMaterialInfo',
	        sortable: true,   
	        pageList : [ 1,5,10],
	        pageSize : 10,
	        sidePagination: "client",  
	        clickToSelect : true,
	        pagination : true,
	        queryParams : queryParams,
		});
	   /*  $('#userOperationRecordTable').bootstrapTable({
	        columns:[{
	            field : 'id',
	            title : '零件编号'
	        },{
	            field : 'userID',
	            title : '管理员编号'
	        },{
	            field : 'userName',
	            title : '管理员姓名'
	        },{
	            field : 'operationName',
	            title : '支取情况'
	        },{
	            field : 'operationTime',
	            title : '时间'
	        },{
	            field : 'resultNumber',
	            title : '剩余数量'
	        }],
	        url : 'data/kits.json',
	        method : 'GET',
	        queryParams : queryParams,
            sidePagination : "server",
            dataType : 'json',
            pagination : true,
            pageNumber : 1,
            pageSize : 5,
            pageList : [ 5, 10, 25, 50, 100 ],
            clickToSelect : true,
            
	    }); */
	}

	// 琛ㄦ牸鍒锋柊
	function tableRefresh() {
		$('#userOperationRecordTable').bootstrapTable('refresh', {
			query : {}
		});
	}

	// 鍒嗛〉鏌ヨ鍙傛暟
	function queryParams(params) {
		var temp = {
			limit : params.limit,
			offset : params.offset,
			userID : search_user_id,
			startDate : search_start_date,
			endDate : search_end_date
		}
		return temp;
	}

    // 鏌ヨ鎿嶄綔
    function searchActionInit(){
        $('#search_button').click(function(){
            search_user_id = $('#user_id').val();
            search_start_date = $('#start_date').val();
            search_end_date = $('#end_date').val();
            tableRefresh();
        })
    }
</script>
<div class="panel panel-default">
    <ol class="breadcrumb">
        <li>查询</li>
    </ol>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-3">
                <form action="" class="form-inline">
                    <div class="form-group">
                        <label class="form-label">用户ID</label>
                        <input type="text" id="user_id" class="form-control" placeholder="用户ID" style="width:50%">
                    </div>
                </form>
            </div>
            <div class="col-md-7">
                <form action="" class="form-inline">
                    <label class="form-label">日期范围</label>
                    <input class="form_date form-control" id="start_date" placeholder="开始日期">
                    <label class="form-label">&nbsp;&nbsp;-&nbsp;&nbsp;</label>
                    <input class="form_date form-control" id="end_date" placeholder="结束日期">
                </form>
            </div>
            <div class="col-md-2">
                <button class="btn btn-success" id="search_button">
                    <span class="glyphicon glyphicon-search"></span> <span>查询</span>
                </button>
            </div>
        </div>
        <div class="row" style="margin-top:25px">
            <div class="col-md-12">
                <table class="table table-striped" id="userOperationRecordTable"></table>
            </div>
        </div>
    </div>
</div>