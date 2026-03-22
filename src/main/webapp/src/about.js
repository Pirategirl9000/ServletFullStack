document.addEventListener("DOMContentLoaded", ()=>{
    const images = document.querySelectorAll("img");

    // Attach links to images
    for (const image of images) {
        if (!image.hasAttribute("data-link")) continue;

        image.addEventListener("click", ()=>location.href=image.getAttribute("data-link"));
    }
})