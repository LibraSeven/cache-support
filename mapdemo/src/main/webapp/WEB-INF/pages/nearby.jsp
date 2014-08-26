<%--
  Created by IntelliJ IDEA.
  User: louis
  Date: 2014/8/26
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <title>Hello</title>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=1.5&ak=142996c97a6e22ed85ea1419e0a9dc75"></script>
    <script type="text/javascript" src="<c:url value="/resources/geo.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/jquery-2.1.1.min.js"/>"></script>
    <style type="text/css">
        html {
            height: 100%
        }

        body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

        #container {
            height: 100%
        }
    </style>
</head>
<body>
<div id="container"></div>


<script type="text/javascript">
    var map = new BMap.Map("container");
    var point = new BMap.Point(121.457653116071, 31.157123545163);
    map.centerAndZoom(point, 15);
    map.enableScrollWheelZoom();
    addMarker(map, point);

    $(function(){
        $.ajax({
            type:'post',
            url:'<c:url value="/geo/getNearBy.do"/>',
            data:{lng:point.lng,lat:point.lat},
            success:function(data){
                if(data){
                    for(var i=0;i<data.length;i++){
                        var coordinates=data[i].loc.coordinates;
                        addMarker(map,new BMap.Point(coordinates[0],coordinates[1]));
                    }
                }
            },
            error:function(e){
                alert(e.responseText);
            }
        });
    });
</script>

</body>
</html>
