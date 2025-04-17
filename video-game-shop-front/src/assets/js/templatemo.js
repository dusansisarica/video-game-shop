/*

TemplateMo 559 Zay Shop

https://templatemo.com/tm-559-zay-shop

*/

'use strict';
$(document).ready(function () {
  var all_panels = $('.templatemo-accordion > li > ul').hide();

  // Set up the click event for the accordion links
  $(".templatemo-accordion > li > a").click(function () {
    var targetUl = $(this).next(); // Get the <ul> associated with the clicked link
    var otherUl = $(".templatemo-accordion > li > ul").not(targetUl); // Get other <ul> elements

    // Stop any ongoing animation before starting a new one
    all_panels.stop(true, true);

    if (targetUl.hasClass("active")) {
      // If the clicked panel is already open, close it
      targetUl.removeClass("active").slideUp();
    } else {
      // Close all other panels
      otherUl.removeClass("active").slideUp();

      // Open the clicked panel
      targetUl.addClass("active").slideDown();
    }

    return false;

    // Accordion
    // var all_panels = $('.templatemo-accordion > li > ul').hide();

    // $(".templatemo-accordion > li > a").click(function () {
    //   var targetUl = $(this).next(); // Get the <ul> associated with the clicked link
    //   var otherUl = $(".templatemo-accordion > li > ul").not(targetUl); // Get other <ul> elements

    //   if (targetUl.hasClass("active")) {
    //     targetUl.removeClass("active").slideUp();
    //   } else {
    //     // Close other <ul> elements (if they are open)
    //     otherUl.removeClass("active").slideUp();

    //     // Open the clicked <ul>
    //     targetUl.addClass("active").slideDown();
    //   }

    //   return false;

    var target = $(this).next();
    if (!target.hasClass('active')) {
      all_panels.removeClass('active').slideUp();
      target.addClass('active').slideDown();
    }
    return false;
  });
  // End accordion

  // Product detail
  $('.product-links-wap a').click(function () {
    var this_src = $(this).children('img').attr('src');
    $('#product-detail').attr('src', this_src);
    return false;
  });
  $('#btn-minus').click(function () {
    var val = $("#var-value").html();
    val = (val == '1') ? val : val - 1;
    $("#var-value").html(val);
    $("#product-quanity").val(val);
    return false;
  });
  $('#btn-plus').click(function () {
    var val = $("#var-value").html();
    val++;
    $("#var-value").html(val);
    $("#product-quanity").val(val);
    return false;
  });
  $('.btn-size').click(function () {
    var this_val = $(this).html();
    $("#product-size").val(this_val);
    $(".btn-size").removeClass('btn-secondary');
    $(".btn-size").addClass('btn-success');
    $(this).removeClass('btn-success');
    $(this).addClass('btn-secondary');
    return false;
  });
  // End roduct detail

});
