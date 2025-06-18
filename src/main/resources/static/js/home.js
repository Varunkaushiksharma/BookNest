// Handle all "Read" buttons
document.addEventListener("DOMContentLoaded", function () {
  const readButtons = document.querySelectorAll(".read");

  readButtons.forEach((btn, index) => {
    btn.addEventListener("click", (e) => {
      // Avoid affecting actual links
      if (btn.tagName.toLowerCase() !== "a") {
        e.preventDefault();
        alert(`Opening Book ${index + 1}...`);
        // You can add routing logic or modal open here
      }
    });
  });

  // Optional: Sticky navbar on scroll
  const nav = document.querySelector("nav");
  window.addEventListener("scroll", () => {
    if (window.scrollY > 50) {
      nav.classList.add("sticky");
    } else {
      nav.classList.remove("sticky");
    }
  });
});

const toggle = document.querySelector('.nav-toggle');
const navLinks = document.querySelector('nav ul');

toggle.addEventListener('click', () => {
  navLinks.classList.toggle('show');
});

