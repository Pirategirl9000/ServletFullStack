document.addEventListener("DOMContentLoaded", ()=>{
    // Used for adding image links to documentation pages
    const images = document.querySelectorAll("img");

    // Used for copying the schema
    const copyButton = document.querySelector("#schema-button");
    const copyConfirm = copyButton.nextElementSibling;
    const schemaDataElement = document.querySelector("#schema-data");

    // Used for showing schema information
    const showSchemaButton = document.querySelector("#show-schema-button");
    const schemaSection = document.querySelector("#schema");

    // Attach links to images
    for (const image of images) {
        if (!image.hasAttribute("data-link")) continue;

        image.addEventListener("click", ()=>location.href=image.getAttribute("data-link"));
    }

    // Attach listener for copying schema text
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

    // Attach listener to the show schema button to toggle the visibility of the schema
    showSchemaButton.addEventListener("click", ()=>schemaSection.classList.toggle("is-hidden"));
})