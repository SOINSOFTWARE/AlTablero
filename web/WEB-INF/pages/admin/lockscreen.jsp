<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="lockscreen">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Al Tablero</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />
        <link href="<c:url value="/res/css/bootstrap.min.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/res/css/font-awesome.min.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/res/css/AdminLTE.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="center">            
            <div class="headline text-center" id="time"></div>
            
            <div class="lockscreen-name">${username}</div>
            
            <div class="lockscreen-item">
                <div class="lockscreen-image">
                    <img src="<c:url value="/res/img/${avatar}.png" />" alt="user image"/>
                </div>

                <div class="lockscreen-credentials">
                    <div class="input-group">
                        <input type="password" class="form-control" placeholder="password" />
                        <div class="input-group-btn">
                            <button class="btn btn-flat"><i class="fa fa-arrow-right text-muted"></i></button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="lockscreen-link">
                <a href="login.html">Or sign in as a different user</a>
            </div>
        </div>

        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="<c:url value="/res/javascript/bootstrap.min.js" />" type="text/javascript"></script>

        <!-- page script -->
        <script type="text/javascript">
            $(function() {
                startTime();
                $(".center").center();
                $(window).resize(function() {
                    $(".center").center();
                });
            });

            /*  */
            function startTime()
            {
                var today = new Date();
                var h = today.getHours();
                var m = today.getMinutes();
                var s = today.getSeconds();

                // add a zero in front of numbers<10
                m = checkTime(m);
                s = checkTime(s);

                //Check for PM and AM
                var day_or_night = (h > 11) ? "PM" : "AM";

                //Convert to 12 hours system
                if (h > 12)
                    h -= 12;

                //Add time to the headline and update every 500 milliseconds
                $('#time').html(h + ":" + m + ":" + s + " " + day_or_night);
                setTimeout(function() {
                    startTime()
                }, 500);
            }

            function checkTime(i)
            {
                if (i < 10)
                {
                    i = "0" + i;
                }
                return i;
            }

            /* CENTER ELEMENTS IN THE SCREEN */
            jQuery.fn.center = function() {
                this.css("position", "absolute");
                this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) +
                        $(window).scrollTop()) - 30 + "px");
                this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) +
                        $(window).scrollLeft()) + "px");
                return this;
            }
        </script>
    </body>
</html>
