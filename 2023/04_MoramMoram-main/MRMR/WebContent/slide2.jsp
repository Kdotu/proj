<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>Swiper demo</title>
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"
    />
    <!-- Link Swiper's CSS -->
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css"
    />

    <!-- Demo styles -->
    <style>
      html,
      body {
        position: relative;
        height: 100%;
      }


      .swiper {
        width: 100%;
        padding-top: 50px;
        padding-bottom: 50px;
      }

      .swiper-slide {
        background-position: center;
        background-size: cover;
        width: 300px;
        height: 300px;
      }

      .swiper-slide img {
        display: block;
        width: 100%;
        
      }
    </style>
  </head>

  <body>
    	<h3 style="text-align: center;margin-top: 100px;">지금 인기 있는 전문가</h3>
    <!-- Swiper -->
    <div class="swiper mySwiper1">
      <div class="swiper-wrapper">
        <div class="swiper-slide">
          <img src="./img/전문가 (1).png">
        </div>
        <div class="swiper-slide">
          <img src="./img/전문가 (2).png">
        </div>
        <div class="swiper-slide">
          <img src="./img/전문가 (3).png">
        </div>
        <div class="swiper-slide">
          <img src="./img/전문가 (4).png">
        </div>
        <div class="swiper-slide">
          <img src="./img/전문가 (5).png">
        </div>
        <div class="swiper-slide">
          <img src="./img/전문가 (6).png">
        </div>
        <div class="swiper-slide">
          <img src="./img/전문가 (7).png">
        </div>
      </div>
      <div class="swiper-pagination"></div>
    </div>

    <!-- Swiper JS -->
    <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>

    <!-- Initialize Swiper -->
    <script>
      var swiper = new Swiper(".mySwiper1", {
        effect: "coverflow",
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: "auto",
        coverflowEffect: {
          rotate: 50,
          stretch: 0,
          depth: 100,
          modifier: 1,
          slideShadows: true,
        },
        pagination: {
          el: ".swiper-pagination",
        },
      });
    </script>
  </body>
</html>
