document.addEventListener("DOMContentLoaded", ()=>{
    const images = document.querySelectorAll("img");

    // Attach links to images
    for (const image of images) {
        if (!image.hasAttribute("data-link")) continue;

        image.addEventListener("click", ()=>location.href=image.getAttribute("data-link"));
    }

    const copyButton = document.querySelector("#schema-button");
    const copyConfirm = copyButton.nextElementSibling;
    const schemaDataElement = document.querySelector("#schema-data");

    copyButton.addEventListener("click",  ()=>{
        navigator.clipboard.writeText(schemaDataElement.textContent)
            .then(()=>{
                // Success at copying
                copyConfirm.textContent = "Copied";
            })
            .catch(()=>{
                // Failed to copy
                copyConfirm.textContent = "Failed to Copy";
            })
            // Either way show the result
            .finally(()=>{
                copyConfirm.classList.remove("is-hidden");
        });
    })

    const showSchemaButton = document.querySelector("#show-schema-button");
    const schemaSection = document.querySelector("#schema");

    showSchemaButton.addEventListener("click", ()=>{
        schemaSection.classList.toggle("is-hidden");
    })
})