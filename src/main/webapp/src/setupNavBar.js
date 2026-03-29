const nav = document.querySelector("nav");
const navElements = [
    {text: "Home", link: "/home"},
    {text: "Browse", link: "/browse"},
    {text: "About", link: "/about"},
    {text: "Contact Us", link: "/contact"}
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
        newElement.addEventListener("click", ()=> location.href = element.link);

        // Add the nav element
        nav.appendChild(newElement);
    }
}


document.addEventListener("DOMContentLoaded", ()=> {
    initializeNavBar();
})