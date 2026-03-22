const navDivs = document.querySelectorAll("nav div");


document.addEventListener("DOMContentLoaded", ()=> {
    for (const div of navDivs) {
        // If it has the data link we redirect to the page
        if (!div.hasAttribute("data-link")) return;

        div.addEventListener("click", ()=>{
            location.href = div.getAttribute("data-link");
        })
    }




})