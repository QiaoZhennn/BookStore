<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/WEB-INF/include/base.jsp" %>
	<script type="text/javascript">
		$(function () {
			$(".inputCount").change(function () {
			    //可以通过defaultValue获取<input>的改变前的值。
			    var defValue=this.defaultValue;
				var inputCount=$(this).val();
				var bookId=$(this).attr("id");
				var reg=/^\+?[1-9][0-9]*$/;
				if(!reg.test(inputCount)){
				    //输入值不合法
				    alert("Please input positive integer");
				    this.value=defValue;
				    return false;
				}
				var stock=$(this).attr("name");
				stock=parseInt(stock);
				inputCount=parseInt(inputCount);
				if(inputCount>stock){
				    //已经超过库存，数量恢复默认
                    this.value=defValue;
					alert("Max stock is "+stock);
					return false;
				}else {
                    this.defaultValue=inputCount;
                }
				var url="CartServlet?method=updateCartItem";
				var params={"bookId":bookId,"bookCount":inputCount};
				var $tdEle=$(this).parent().next().next();
				$.post(url,params,function (data) {
					//获取JSON字符串中的三个对象
					var totalCount=data.totalCount;
                    $("#b_count").text(totalCount);
                    var totalAmount=data.totalAmount;
                    $("#b_price").text(totalAmount);
                    var amount=data.amount;
                    $tdEle.text(amount);
                },"json");
            });
        });
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="/static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/WEB-INF/include/welcome.jsp"%>

	</div>
	
	<div id="main">
	<c:if test="${empty cart.cartItems}">
		<h1>购物车还空着，<a href="index.jsp" style="color: #d42;">去购物</a> </h1>
	</c:if>
		<c:if test="${not empty cart.cartItems}">


		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${cart.cartItems}" var="cartItem">
				<tr>
					<td>${cartItem.book.title}</td>
					<td>
						<input name="${cartItem.book.stock}" id="${cartItem.book.id}" class="inputCount" type="text" value="${cartItem.count}" style="width: 40px;text-align: center">
					</td>
					<td>${cartItem.book.price}</td>
					<td>${cartItem.amount}</td>
					<td><a href="CartServlet?method=deleteCartItem&bookId=${cartItem.book.id}">删除</a></td>
				</tr>
			</c:forEach>
			
		</table>



		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count" id="b_count">${cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price" id="b_price">${cart.totalAmount}</span>元</span>
			<span class="cart_span"><a href="index.jsp">继续购物</a></span>
			<span class="cart_span"><a href="CartServlet?method=emptyCart">清空购物车</a></span>
			<span class="cart_span"><a href="OrderClientServlet?method=checkout">去结账</a></span>
		</div>
		</c:if>
	</div>
	
	<div id="bottom">
		<span>
		</span>
	</div>
</body>
</html>