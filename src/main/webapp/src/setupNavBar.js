const nav = document.querySelector("nav");
const navElements = [
    {text: "Home", link: "index.html"},
    {text: "Browse", link: "browse.html"},
    {text: "About", link: "about.html"},
    {text: "Contact Us", link: "contact.html"}
]

/**
 * For every nav bar element we add it's corresponding redirect link
 */
function initializeNavBar() {
    for (const element of navElements) {
        // Create a div element for the nav bar
        const newElement = document.createElement('div');

        // Add the relevant info to the div elements
        newElement.textContent = element.text;
        newElement.addEventListener("click", ()=>location.href=element.link);

        // Add the nav element
        nav.appendChild(newElement);
    }
}


document.addEventListener("DOMContentLoaded", ()=> {
    initializeNavBar();
})