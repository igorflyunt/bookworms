<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="../../layout/header.jsp"/>
<body>
<jsp:include page="../../layout/navbar.jsp"/>
<div class="container-fluid">
            <div class="row mx-auto">
                    <div class="col-sm-6 mx-auto">
                        <ul class="list-unstyled">
                    <li class="media my-4 ">
                        <div class="container media-object mr-3" style="width: auto" >
                            <div class="row mx-auto">
                                <img class="book-cover" src="https://images-na.ssl-images-amazon.com/images/I/51K84pomCRL._SX305_BO1,204,203,200_.jpg" alt="...">
                            </div>
                            <div class="row my-3 mx-auto">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Want to read
                                    </button>
                                    <div class="dropdown-menu">
                                        <form  method="post" action="/user/bookshelf" >
                                            <input type="hidden" name="bookId" value="${book.id}">
                                            <button type="submit" class="btn btn-secondary btn-sm dropdown-item" value="to-read"
                                                    name="shelf">Want to read</button>
                                            <button type="submit" class="btn btn-secondary btn-sm dropdown-item" value="read"
                                                    name="shelf">Read</button>
                                            <button type="submit" class="btn btn-secondary btn-sm dropdown-item" value="currently-reading"
                                                    name="shelf">Currently reading</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="media-body text-left">
                            <h5 class="mt-0 mb-1"><a class="custom-link" href="/book?bookid=${book.id}">${book.name}</a></h5>
                            <p>Publication year: ${book.firstPublished}</p>
                            by
                            <c:forEach items="${book.authors}" var="author" varStatus="loop">
                                <small>
                                    <a class="custom-link" href="/author?authorId=${author.id}">${author.firstName} ${author.lastName}
                                    </a>${loop.last ? "" : ",  "}
                                </small>
                            </c:forEach>
                            <p>
                                <c:forEach items="${book.genres}" var="genre">
                                    <span class="badge badge-light">${genre.genreName}</span>
                                </c:forEach>
                            </p>
                            <p class="book-description">
                                ${book.description}
                            </p>
                        </div>
                    </li>
            </ul></div></div>
</div>
<jsp:include page="../../layout/footer.jsp"/>
</body>
</html>
