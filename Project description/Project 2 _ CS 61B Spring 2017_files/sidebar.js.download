/**
 * Scripts for handling the lab sidebar (contains Table of Contents).
 */

var SIDEBAR_JQ = "#sidebar";
var CONTENT_CONTAINER_JQ = "#content-container";
var CONTENT_SPACER_CLASS = "content-spacer";
var TOC_BUTTON_ID = "toc-button";

/**
 * Decide what to do with the sidebar based on the screen size.
 * If the screen is too small, hide the sidebar. Otherwise, show it.
 */
var showOrHideSidebar = function() {
    var screen = $(window)
    if (screen.width() < 1050) {
        hideSidebar();
        addTocButton();
        $(CONTENT_CONTAINER_JQ).removeClass(CONTENT_SPACER_CLASS);
    } else {
        showSidebar();
        removeTocButton();
        $(CONTENT_CONTAINER_JQ).addClass(CONTENT_SPACER_CLASS);
    }
}

var showSidebar = function() {
    $(SIDEBAR_JQ).show();
}

var hideSidebar = function() {
    $(SIDEBAR_JQ).hide();
}

/**
 * If the sidebar is shown, hide it. If hidden, show it.
 * Occurs when user clicks on the Table of Contents button.
 */
var flipSidebar = function() {
    if ($(SIDEBAR_JQ).is(":visible")) {
        hideSidebar();
    } else {
        showSidebar();
    }
}

/**
 * Add a Table of Contents button to the nav bar if there isn't already one.
 */
var addTocButton = function() {
    if (document.getElementById(TOC_BUTTON_ID)) {
        return;
    }
    var tocButton = document.createElement("button");
    tocButton.id = TOC_BUTTON_ID;
    tocButton.innerHTML = '<i class="material-icons md-light">list</i>';
    tocButton.onclick = flipSidebar;

    var navBar = document.getElementById("navbar");
    navBar.insertBefore(tocButton, navBar.firstChild);
}

/**
 * Remove the Table of Contents button from the nav bar if there is one.
 */
var removeTocButton = function() {
    var tocButton = document.getElementById(TOC_BUTTON_ID);
    if (tocButton) {
       tocButton.parentNode.removeChild(tocButton);
    }
}

$(document).ready(showOrHideSidebar);

$(window).bind('resize', function() {
    showOrHideSidebar();
});
