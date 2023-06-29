<%@page import="java.sql.ResultSet"%>
<%@page import="jdbc.connectDB"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
    if (session.getAttribute("admin-username") == null) {
        response.sendRedirect(request.getContextPath() + "/admin/login");
    }
%>
<!-- Start header section --> 
<jsp:include page = "./header/header.jsp" flush = "true" /> 
<div class="content-wrapper"> 
    <div class="container-fluid"> 
        <!--End Row--> 
        <div class="row"> 
            <div class="col-lg-12"> 
                <button class="add-catalog"><a href="${pageContext.request.contextPath}/admin/admin/add">Thêm Admin</a></button> 
            </div> 
            <div class="col-lg-12"> 
                <div class="card"> 
                    <div class="card-body"> 
                        <h5 class="card-title">Danh sách Admin</h5> 
                        <div class="table-responsive">              
                            <table class="table table-striped"> 
                                <thead> 
                                    <tr> 
                                        <th scope="col">#</th> 
                                        <th scope="col">Tên đăng nhập</th> 
                                        <th scope="col">Tên Admin</th>
                                        <th scope="col">Hành động</th>
                                        <th scope="col">Ngày tạo</th> 
                                    </tr> 
                                </thead> 
                                <tbody> 
                                    <c:forEach items="${adminlist}" var="admin"> 
                                        <tr> 
                                            <td scope="row">${admin.id}</td> 
                                            <td>${admin.username}</td> 
                                            <td>${admin.name}</td> 
                                            <td> 
                                                <button class="btn btn-danger"><a href="${pageContext.request.contextPath}/admin/admin/delete?admin-id=${admin.id}">Xóa</a></button>

                                                <button class="btn btn-success"><a href="${pageContext.request.contextPath}/admin/admin/edit?id=${admin.id}">Sửa</a></button>
                                            </td> 
                                            <td>${admin.created}</td>
                                        </tr> 
                                    </c:forEach> 
                                </tbody> 
                            </table>
                             <div class="pagination" style="align-items: center;justify-content: center">

                                <c:if test="${tag > 1}">
                                    <a href="${pageContext.request.contextPath}/admin/admin/list?index=${tag - 1}">&laquo;</a>
                                </c:if>

                                <c:forEach begin="${beginP}" end="${maxP}" var="i">
                                    <a class="${tag == i?"active" : ""}" href="${pageContext.request.contextPath}/admin/admin/list?index=${i}">${i}</a>
                                </c:forEach>
                                <c:if test="${tag < maxP}">
                                    <a href="${pageContext.request.contextPath}/admin/admin/list?index=${tag + 1}">&raquo;</a>
                                </c:if>
                            </div>
                        </div> 
                    </div> 
                </div> 
            </div> 
        </div> 
    </div> 
</div> 

<jsp:include page = "./footer/footer.jsp" flush = "true" />