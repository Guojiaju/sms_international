<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${pageList.allPages > 0}">
<script type="text/javascript">
//分页
$(function(){
//    $("#pageSizeSelect").uedSelect({width : 50});
     $('#btn_search').on('click', function(){
		$('#currentPage').val(1);
	});
	$('#homePage').click(function(){
		$('#currentPage').val(1);
		$('#searchForm').submit();
	});
	$('#prePage').click(function(){
		$('#currentPage').val(parseInt($('#currentPage').val()) - 1);
		$('#searchForm').submit();
	});
	$('#pageUl a[id^="page_"]').click(function(){
		var id = $(this).attr('id');
		currentPage = id.substring(id.indexOf('_') + 1);
		$('#currentPage').val(currentPage);
		$('#searchForm').submit();
	});
	$('#nextPage').click(function(){
		$('#currentPage').val(parseInt($('#currentPage').val()) + 1);
		$('#searchForm').submit();
	});
	$('#lastPage').click(function(){
		$('#currentPage').val($('#allPages').val());
		$('#searchForm').submit();
	});
	$('#pageSizeSelect').change(function(){
		$('#pageSize').val($(this).val());
		$('#searchForm').submit();
	});
	$('#currentPageInput').keydown(function(event){
		var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==13){ // enter 键
        	var val = $(this).val();
        	if(!isNaN(val) && parseInt(val) <= $('#allPages').val()){
         		$('#currentPage').val(val);
         		//alert(val+",1");
        	}else{
        		//alert(val+",2");
        		$('#currentPage').val(1);
        		$(this).val(1);
        	}
        	//alert(val+",3");
    		$('#searchForm').submit();
        }
	});
});
</script>
<input type="hidden" name="currentPage" value="${pageList.currentPage}" id="currentPage"/>
<input type="hidden" value="${pageList.allPages }" id="allPages"/>
<input type="hidden" name="pageSize" value="${pageList.pageSize }" id="pageSize"/>
<nav aria-label="Page navigation" style="height:30px;">
<span style="margin-right: 50px;height:50px;float:left;line-height:30px;">
	<b>共</b>&nbsp;
	<b style="color:green;">${pageList.rows }</b>&nbsp;
	<b>条记录</b></span>
  	<ul class="pagination" id="pageUl" style="float:left;margin:0px;height:80px;">
	  	<c:if test="${pageList.currentPage > pageList.homePage }">
    		<li class="paginItem">
    			<a href="javascript:void(0)" id="prePage"  aria-label="Previous">
    				<span aria-hidden="true">&laquo;</span>
    			</a>
    		</li>
	  	</c:if>
	  	<c:choose>
		  	<c:when test="${pageList.allPages < 7 }">
		 	  	<c:forEach begin="1" end="${pageList.allPages }" varStatus="i">
		 	  		<li class="paginItem">
		 	  			<a <c:if test="${i.index == pageList.currentPage }">class="current"</c:if> href="javascript:void(0)" id="page_${i.index }">${i.index }</a>
		 	  		</li>
		 	  	</c:forEach>
		  	</c:when>
		  	<c:when test="${pageList.currentPage < 4 }">
		 	  	<c:forEach begin="1" end="7" varStatus="i">
		 	  		<li class="paginItem">
		 	  			<a <c:if test="${i.index == pageList.currentPage }">class="current"</c:if> href="javascript:void(0)" id="page_${i.index }">${i.index }</a>
		 	  		</li>
		 	  	</c:forEach>
		  	</c:when>
	  		<c:when test="${pageList.currentPage >= (pageList.lastPage - 3) }">
 	  			<c:forEach begin="${pageList.lastPage - 6}" end="${pageList.lastPage}" varStatus="i">
 	  				<li class="paginItem">
 	  					<a <c:if test="${i.index == pageList.currentPage }">class="current"</c:if> href="javascript:void(0)" id="page_${i.index }">${i.index }</a>
 	  				</li>
 	  			</c:forEach>
	  		</c:when>
		  	<c:when test="${pageList.currentPage >= 4 }">
		 	  	<c:forEach begin="${pageList.currentPage - 3}" end="${pageList.currentPage + 3}" varStatus="i">
		 	  		<li class="paginItem" >
		 	  			<a <c:if test="${i.index == pageList.currentPage }">class="current"</c:if> href="javascript:void(0)" id="page_${i.index }">${i.index }</a>
		 	  		</li>
		 	  	</c:forEach>
		  	</c:when>
	  	</c:choose>
	  	<c:if test="${pageList.currentPage < pageList.lastPage }">
    		<li class="paginItem">
    			<a href="javascript:void(0)" id="nextPage" aria-label="Previous">
    				<span aria-hidden="true">&raquo;</span>
    			</a>
    		</li>
	  	</c:if>
  	</ul>
</nav>
</c:if>