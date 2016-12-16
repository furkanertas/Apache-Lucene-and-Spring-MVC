<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Ada">

    <title>Apache Lucene</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,700italic,800,800italic,700,600italic" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">

    <!-- Vendor Styles -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/font-awesome.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/owl.carousel.css"/>" rel="stylesheet">

    <!-- Theme Styles -->
    <link href="<c:url value="/resources/css/ada.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/ada-themes.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">

    <!-- Themeswitcher - REMOVE THIS LINE -->
    <link href="<c:url value="/resources/css/themeswitcher.css"/>" rel="stylesheet">

    <!-- HTML5 shiv for IE8 support -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <![endif]-->
</head>

<body>

<div class="loading"></div>

<div id="header">
    <div class="top">
        <a href="/lucene">
            <h1>Apache Lucene ile arama yap</h1>
        </a>
    </div>
</div>

<div id="documentation">
    <div class="container">
        <div class="col-md-12">
            <div class="block" id="en">
                <div class="form-inline text-center">
                    <div class="form-group">
                        <input type="text" class="form-control" name="tr" placeholder="Kelime ara" autofocus>
                    </div>
                    <button class="btn btn-primary btn-lg" id="show-btn"><i class="fa fa-check"></i> Sonuçları getir</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="footer">
    <div id="subscribe">
        <div class="container">
            <div class="col-lg-12">
                <img src="<c:url value="/resources/img/ring.gif"/>" id="ring">
            </div>
            <div class="col-lg-12">
                <div class="row">
                    <p class="text-center lead text-info" id="result-info"> </p>
                </div>
            </div>
            <div class="col-lg-12" id="search-box">

            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/js/jquery-1.11.3.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/owl.carousel.js"/>"></script>
<script src="<c:url value="/resources/js/smoothScroll.js"/>"></script>
<script src="<c:url value="/resources/js/wow.min.js"/>"></script>
<script src="<c:url value="/resources/js/main.js"/>"></script>
<script src="<c:url value="/resources/js/themeswitcher.js"/>"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#ring').hide();
        $('#show-btn').click(function () {
            var input = $("input[name=tr]").val().toLowerCase();
            var word = 'word='+input;
            $('#search-box').html(" ");
            $('#result-info').html(" ");
            $('#ring').show();
            $.ajax  ({
                url:'search',
                type:"post",
                data:word,
                dataType:"json",
                success:function(res){
                    $('#ring').hide();
                    $('#result-info').html('Yaklaşık '+(res.length-1)+' sonuç bulundu ('+res[(res.length-1)].text+' saniye)');
                    for (var i=0; i<(res.length-1); i++)
                        $('#search-box').append('<div class="row"><p class="text-left">'+(i+1)+' '+res[i].text+'</p></div>')
                },error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.status);
                    alert(thrownError);
                }
            });
        })

    });
</script>
</body>
</html>