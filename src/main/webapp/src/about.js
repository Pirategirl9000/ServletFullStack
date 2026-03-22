document.addEventListener("DOMContentLoaded", ()=>{
    const images = document.querySelectorAll("img");

    // Attach links to images
    for (const image of images) {
        if (!image.hasAttribute("data-link")) continue;

        image.addEventListener("click", ()=>location.href=image.getAttribute("data-link"));
    }

    const copyButton = document.querySelector("#schema-button");

    copyButton.addEventListener("click", ()=>{
        navigator.clipboard.writeText(copyButton.nextElementSibling.textContent);
    })

    const showSchemaButton = document.querySelector("#show-schema-button");
    const schemaSection = document.querySelector("#schema");

    showSchemaButton.addEventListener("click", ()=>{
        schemaSection.classList.toggle("is-hidden");
    })
})