$(function ($) {

    $(".aa-cartbox").hover(function () {
        $(this).find(".aa-cartbox-summary").fadeIn(500);
    }
    , function () {
        $(this).find(".aa-cartbox-summary").fadeOut(500);
    }
    );

    $('[data-toggle="tooltip"]').tooltip();
    $('[data-toggle2="tooltip"]').tooltip();


    $('#demo-1 .simpleLens-thumbnails-container img').simpleGallery({
        loading_image: 'demo/images/loading.gif'
    });

    $('#demo-1 .simpleLens-big-image').simpleLens({
        loading_image: 'demo/images/loading.gif'
    });

    $('.aa-popular-slider').slick({
        dots: false,
        infinite: false,
        speed: 300,
        slidesToShow: 4,
        slidesToScroll: 4,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
        ]
    });

    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.scrollToTop').fadeIn();
        } else {
            $('.scrollToTop').fadeOut();
        }
    });

    $('.scrollToTop').click(function () {
        $('html, body').animate({scrollTop: 0}, 800);
        return false;
    });

    $(window).load(function () { // makes sure the whole site is loaded      
        $('#wpf-loader-two').delay(200).fadeOut('slow'); // will fade out      
    });

    $('.aa-related-item-slider').slick({
        dots: false,
        infinite: false,
        speed: 300,
        slidesToShow: 4,
        slidesToScroll: 4,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
        ]
    });
    $(".aa-product-selling").parent("li").addClass("aa-product-sellings-box-shadow");

//    var dispayPreview = document.querySelector("#image-preview");
//    var btnChoose = document.querySelector("#btn-choose-image");
//    var img = document.querySelector("#image");
//    dispayPreview.addEventListener("change", function () {
//        var file = this.files[0];
//        if (file) {
//            var reader = new FileReader();
//            reader.onload = function () {
//                var result = reader.result;
//                img.src = result;
//            }
//            reader.readAsDataURL(file);
//        }
//    });
//    $('#image-upload').change(function () {
//        var fileInput = document.getElementById("image-upload");
//        var file = fileInput.files[0];
//        var fileName = file.name;
//        var size = file.size;
//        var data = new FormData();
//        data.append("image", file);
//        $.ajax({
//            data: data,
//            type: "POST",
//            url: "/7Seven/view/client/user/upload?size=" + size, // this file uploads the picture and 
//            cache: false,
//            contentType: false,
//            processData: false,
//            success: function () {
//                var image = "/7Seven/view/client/assets/images/avatar/" + fileName;
//                $('#image').attr('src', image);
//            },
//            error: function (data) {
//                console.log(data);
//            }
//        });
//    });
    
    $(".aa-animation-board-new").slick({
        draggable: true,
        autoplay: true,
        autoplaySpeed: 1000,
        infinite: true,
        slidesToShow: 4,
        slidesToScroll: 1,
        touchThreshold: 1000,
        arrows: false,
        dots: false,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1,
                    infinite: true,
                    arrows: false,
                },
            },
            {
                breakpoint: 768,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1,
                    infinite: true,
                    arrows: false,
                },
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    infinite: true,
                    arrows: false,
                },
            },
        ],
    });
});

