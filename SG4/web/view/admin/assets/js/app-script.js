$(function () {
    "use strict";

    $.sidebarMenu($('.sidebar-menu'));

    $(".toggle-menu").on("click", function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });


    $(function () {
        for (var i = window.location, o = $(".sidebar-menu a").filter(function () {
            return this.href == i;
        }).addClass("active").parent().addClass("active"); ; ) {
            if (!o.is("li"))
                break;
            o = o.parent().addClass("in").parent().addClass("active");
        }
    }),
            $(document).ready(function () {
        $(window).on("scroll", function () {
            if ($(this).scrollTop() > 60) {
                $('.topbar-nav .navbar').addClass('bg-dark');
            } else {
                $('.topbar-nav .navbar').removeClass('bg-dark');
            }
        });

    });


    $(document).ready(function () {
        $(window).on("scroll", function () {
            if ($(this).scrollTop() > 300) {
                $('.back-to-top').fadeIn();
            } else {
                $('.back-to-top').fadeOut();
            }
        });

        $('.back-to-top').on("click", function () {
            $("html, body").animate({scrollTop: 0}, 600);
            return false;
        });
    });


    $(function () {
        $('[data-toggle="popover"]').popover();
    })


    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
    $(".switcher-icon").on("click", function (e) {
        e.preventDefault();
        $(".right-sidebar").toggleClass("right-toggled");
    });
    $('#summernote').summernote({
        height: ($(window).height() - 300),
        callbacks: {
            onImageUpload: function (image) {
                uploadImage(image[0]);
            }
        }
    });
    function uploadImage(image) {
        var filename = image.name;
        var size = image.size;
        var data = new FormData();
        data.append("image", image);
        $.ajax({
            data: data,
            type: "POST",
            url: "/7Seven/admin/product/upload?size="+size, // this file uploads the picture and 
            // returns a chain containing the path
            cache: false,
            contentType: false,
            processData: false,
            success: function () {
                var image = "/7Seven/view/client/assets/images/" + filename;
                $('#summernote').summernote("insertImage", image);
            },
            error: function (data) {
                console.log(data);
            }
        });
    }

});