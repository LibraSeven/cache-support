/**
 * Created by louis on 2014/8/26.
 */

/**
 * 添加标注
 * @param map
 * @param point
 * @returns {BMap.Marker}
 */
function addMarker(map,point){
    var icon = new BMap.Icon("http://api.map.baidu.com/img/markers.png", new BMap.Size(23, 25), {
        offset: new BMap.Size(10, 25),  // 角各偏移10像素和25像素
        imageOffset: new BMap.Size(0, 0 - 10 * 25)  // 设置图片偏移
    });

    var marker = new BMap.Marker(point, {
        icon:icon,
        enableDragging:true,
        raiseOnDrag:true
    });
    map.addOverlay(marker); // 添加标注对象到地图
    return marker;
}

/**
 * 移除标注
 * @param map
 * @param marker
 */
function removeMarker(map,marker){
    map.removeOverlay(marker);
}

/**
 * 添加矩形覆盖物
 * @param pStart
 * @param pEnd
 * @param map
 * @returns {BMap.Polygon}
 */
function addRectangle(pStart,pEnd,map){
    var polygon = new BMap.Polygon([
        new BMap.Point(pStart.lng,pStart.lat),
        new BMap.Point(pEnd.lng,pStart.lat),
        new BMap.Point(pEnd.lng,pEnd.lat),
        new BMap.Point(pStart.lng,pEnd.lat)
    ], {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});
    map.addOverlay(polygon);
    return polygon;
}

/**
 * 构造椭圆
 * @param center    椭圆中心点
 * @param x         横向经度
 * @param y         纵向纬度
 * @returns {Array}
 * @private
 */
function _addOval(center,x,y){
    var assemble=[];
    var angle;
    var dot;
    var tangent=x/y;
    for(var i=0;i<36;i++)
    {
        angle = (2* Math.PI / 36) * i;
        dot = new BMap.Point(center.lng+Math.sin(angle)*y*tangent, center.lat+Math.cos(angle)*y);
        assemble.push(dot);
    }
    return assemble;
}

/**
 * 添加椭圆
 * @param center        圆中心点
 * @param x             横向经度
 * @param y             纵向纬度
 * @param map
 * @returns {BMap.Polygon}
 */
function addOval(center,x,y,map){
    var oval = new BMap.Polygon(_addOval(center,x,y), {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});
    map.addOverlay(oval);
    return oval;
}