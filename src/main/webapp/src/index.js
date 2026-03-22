const navDivs = document.querySelectorAll("nav");

/**
 * For every nav bar element we add it's corresponding redirect link
 */
function initializeNavBar() {
    // Add link to all the nav bar divs
    for (const div of navDivs) {
        if (!div.hasAttribute("data-link")) continue;

        div.addEventListener("click", ()=>{
            location.href = div.getAttribute("data-link");
        })
    }

}


document.addEventListener("DOMContentLoaded", ()=> {
    initializeNavBar();
})