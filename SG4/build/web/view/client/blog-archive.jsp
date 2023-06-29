<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/view/client/assets" var="url"/>

  <!-- Start header section -->
  <jsp:include page = "./header/mainHeader.jsp" flush = "true" />
  <!-- / header section -->
  
<!--  content -->
  <!-- catg header banner section -->
  <section id="aa-catg-head-banner">
   <img src="${pageContext.request.contextPath}/view/client/assets/images/archive-banner.png" alt="banner blog">
   <div class="aa-catg-head-banner-area">
     <div class="container">
      <div class="aa-catg-head-banner-content">
        <h2>Tin tức</h2>
        <ol class="breadcrumb">
          <li><a href="${pageContext.request.contextPath}/">Trang chủ</a></li>
          <li style="color:#fff">Tin tức</li>
        </ol>
      </div>
     </div>
   </div>
  </section>
  <!-- / catg header banner section -->

  <!-- Blog Archive -->
  <section id="aa-blog-archive">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="aa-blog-archive-area">
            <div class="row">
              <div class="col-md-12">
                <div class="aa-blog-content">
                  <div class="row" style="display: flex;flex-wrap: wrap;align-items: stretch;">
                  <c:forEach items="${boardnewlist}" var="boardnew">
                    <div class="col-md-4 col-sm-4">
                      <article class="aa-blog-content-single">                        
                        <h4><a href="${pageContext.request.contextPath}/view/client/news-list-detail?id=${boardnew.id}">${boardnew.title}</a></h4>
                        <figure class="aa-blog-img">
                          <a href="${pageContext.request.contextPath}/view/client/news-list-detail?id=${boardnew.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/news/${boardnew.image_link}" alt="blog img" height="180px" width="300px"></a>
                        </figure>
                        <p class="desc-boardnews">${boardnew.description}</p>
                        <div class="aa-article-bottom">
                          <div class="aa-post-author">
                              Đăng bởi <a href="#">
                                  <c:forEach items="${adminlist}" var="ad">
                                      <c:if test="${ad.id == boardnew.author}">
                                          <c:out value="${ad.name}"></c:out>
                                      </c:if>
                                  </c:forEach>
                              </a>
                          </div>
                          <div class="aa-post-date">${boardnew.created}</div>
                        </div>
                      </article>
                    </div>
                    </c:forEach>
                  </div>
                </div>
                <!-- Blog Pagination -->
                 <div class="pagination">

                    <c:if test="${tag > 1}">
                        <a href="${pageContext.request.contextPath}/view/client/news-list?index=${tag - 1}">&laquo;</a>
                    </c:if>

                    <c:forEach begin="1" end="${maxP}" var="i">
                        <a class="${tag == i?"active" : ""}" href="${pageContext.request.contextPath}/view/client/news-list?index=${i}">${i}</a>
                    </c:forEach>
                    <c:if test="${tag < maxP}">
                        <a href="${pageContext.request.contextPath}/view/client/news-list?index=${tag + 1}">&raquo;</a>
                    </c:if>
                </div>
              </div>
             
            </div>
          </div>
        </div>
      </div>
	</div>
  </section>
  <!-- / Blog Archive -->

<!--  end content-->
  
<!--  footer-->
 <jsp:include page = "./footer/footer.jsp" flush = "true" />
<!-- end footer-->
  
  
