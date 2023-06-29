<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <div class="row mt-3">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Thêm tin tức</div>
                        <hr>
                        <form action="${pageContext.request.contextPath}/admin/new/add" method="post" enctype='multipart/form-data'>

                            <div class="form-group">
                                <label for="input-2">Tên tin tức</label>
                                <input type="text" class="form-control" id="input-2" placeholder="Tên tin tức" name="new-title">
                            </div>
                            <div class="form-group">
                                <label for="editor">Nội dung</label>
                                <textarea class="form-control" id="content-news" name="new-content" style="display: none"></textarea>
                                <textarea class="form-control" rows="4" id="editor"></textarea>
                            </div>
                            <script>
                                CKEDITOR.replace('editor');
                            </script>
                            <div class="form-group">
                                <label for="input-3">Hình ảnh</label>
                                <input type="file" class="form-control" id="input-18" placeholder="Địa chỉ hình ảnh" name="new-image_link">
                            </div>
                            <div class="form-group">
                                <label for="input-4">Người đăng</label>
                                <div>
                                    <c:forEach items="${admin}" var="ad">
                                        <select class="form-control valid" id="input-6" name="new-author" required aria-invalid="false">
                                            <option value="${ad.id}">${ad.username}</option>
                                        </select>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="input-5">Ngày đăng</label>
                                <input type="date" class="form-control" id="the-date" name="new-created">
                            </div>
                            <div class="form-footer">
                                <button class="btn btn-danger"><i class="fa fa-times"></i><a href="${pageContext.request.contextPath}/admin/new/list">Hủy</a></button>
                                <button type="submit" class="btn btn-success"><i class="fa fa-check-square-o"></i> Thêm</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="overlay toggle-menu"></div>
    </div>
</div>
<script>
    var date = new Date();

    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();

    if (month < 10)
        month = "0" + month;
    if (day < 10)
        day = "0" + day;

    var today = year + "-" + month + "-" + day;


    document.getElementById('the-date').value = today;

    for (var i in CKEDITOR.instances) {

        CKEDITOR.instances[i].on('change', function () {
            var content = CKEDITOR.instances.editor.getData();
            document.getElementById("content-news").value = content;
        });

    }
</script>
<jsp:include page = "./footer/footer.jsp" flush = "true" />