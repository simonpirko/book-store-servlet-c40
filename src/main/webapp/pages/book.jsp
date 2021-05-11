<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<form>
    <div class="container">
        <div class="row">
            <div class="col">
                <img src="https://cdn.discordapp.com/attachments/323836563563741194/841339956094042132/2.jpg" class="rounded float-start" alt="...">
            </div>
            <div class="col">
                <div class="text">
                    <figure>
                        <blockquote class="blockquote">
                            <h2>Приключения Оливера Твиста</h2>
                        </blockquote>

                        <figcaption class="blockquote-footer">
                            <cite title="Source Title">Автор: Чарльз Диккенс.
                                "Приключения Оливера Твиста" (1837-1839) - второй роман великого английского писателя Чарльза Диккенса, начатый им в то время, когда он еще даже не успел полностью напечатать принесшие ему славу "Посмертные записки Пиквикского клуба". Увлекательная и трогательная история злоключений маленького сироты Оливера Твиста, несмотря на прошедшие с ее написания почти двести лет, по-прежнему не утратила своей увлекательности и трогательности.</cite>
                        </figcaption>
                    </figure>

                    <h3>10,12 руб.</h3>
                    <button type="submit" class="btn btn-warning">Положить в корзину</button>

                </div>
            </div>
        </div>
    </div>




    <div class="container">
        <div class="row">
            <div class="col-6">
                <div class="comment-box lg-2">
                    <h3>Добавить комментарий</h3>
                    <div class="rating">
                        <input type="radio" name="rating" value="5" id="1">
                        <label for="1">1</label>
                        <input type="radio" name="rating" value="4" id="2">
                        <label for="2">2</label>
                        <input type="radio" name="rating" value="3" id="3">
                        <label for="3">3</label>
                        <input type="radio" name="rating" value="2" id="4">
                        <label for="4">4</label>
                        <input type="radio" name="rating" value="1" id="5">
                        <label for="5">5 (Рейтинг)</label>
                    </div>

                    <div class="comment-area">
                        <textarea class="form-control" placeholder="Сообщение" rows="4"></textarea>
                    </div>

                    <div class="comment-bans mt-3">
                        <div class="row">

                            <div class="col-6">
                                <div class="pull-left"> <button class="btn btn-success btn-sm">Отмена</button></div>
                            </div>

                            <div class="col-6">
                                <div class="pull-right"> <button class="btn btn-success send btn-sm">Отправить<i class="fa fa-long-arrow-right ml-1"></i></button></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <section class="content-item" id="comments">
        <div class="container">
            <div class="row">
                <div class="col">

                    <h3>4 комментария</h3>


                    <div class="media">
                        <a class="pull-left" href="#"></a>
                        <div class="media-body">
                            <h4 class="media-heading">Илья</h4>
                            <ul class="list-unstyled list-inline media-detail pull-right">
                                <li><i class="fa fa-thumbs-up"></i>13</li>
                            </ul>
                            <ul class="list-unstyled list-inline media-detail pull-right">
                                <li class=""><a href="">Like</a></li>
                            </ul>
                            <p>Неплохо. Сюжетная линия достаточно интересная. Под конец книги приходится немножко додумывать тайну о наследстве, что в свою очередь является небольшим минусом.</p>
                        </div>
                    </div>



                    <div class="media">
                        <a class="pull-left" href="#"></a>
                        <div class="media-body">
                            <h4 class="media-heading">Денис</h4>
                            <ul class="list-unstyled list-inline media-detail pull-right">
                                <li><i class="fa fa-thumbs-up"></i>6</li>
                            </ul>
                            <ul class="list-unstyled list-inline media-detail pull-right">
                                <li class=""><a href="">Like</a></li>
                            </ul>
                            <p>Позолота на обложке вытирается при первом контакте с руками, страницы в книге местами газетного формата смяты и не обрезаны под книжный формат. Понятно,что цена невысокая, но контроль качества видимо неведом производителю.</p>
                        </div>
                    </div>



                    <div class="media">
                        <a class="pull-left" href="#"></a>
                        <div class="media-body">
                            <h4 class="media-heading">Игорь</h4>
                            <ul class="list-unstyled list-inline media-detail pull-right">
                                <li><i class="fa fa-thumbs-up"></i>1</li>
                            </ul>
                            <ul class="list-unstyled list-inline media-detail pull-right">
                                <li class=""><a href="">Like</a></li>
                            </ul>
                            <p>Отличная книга, но не рекомендую к чтению.</p>
                        </div>
                    </div>



                    <div class="media">
                        <a class="pull-left" href="#"></a>
                        <div class="media-body">
                            <h4 class="media-heading">Артур</h4>
                            <ul class="list-unstyled list-inline media-detail pull-right">
                                <li><i class="fa fa-thumbs-up"></i>3</li>
                            </ul>
                            <ul class="list-unstyled list-inline media-detail pull-right">
                                <li class=""><a href="">Like</a></li>
                            </ul>
                            <p>Интересно. Как всегда, Диккенс на высоте!</p>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </section>
    </div>
</form>
</body>
</html>
