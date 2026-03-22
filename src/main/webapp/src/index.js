const navDivs = document.querySelectorAll("nav div");

/**
 * For every nav bar element we add it's corresponding redirect link
 */
function initializeNavBar() {
    for (const div of navDivs) {
        if (!div.hasAttribute("data-link")) return;

        div.addEventListener("click", ()=>{
            location.href = div.getAttribute("data-link");
        })
    }
}


document.addEventListener("DOMContentLoaded", ()=> {
    initializeNavBar();
})