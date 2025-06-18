// Simple client-side validation for signup form
const signupForm = document.getElementById('signupForm');
if (signupForm) {
  signupForm.addEventListener('submit', (e) => {
    const password = signupForm.password.value.trim();
    const confirmPassword = signupForm.confirmPassword.value.trim();
    
    if (password !== confirmPassword) {
      e.preventDefault();
      alert("Passwords do not match!");
      signupForm.confirmPassword.focus();
    } else if (password.length < 6) {
      e.preventDefault();
      alert("Password must be at least 6 characters.");
      signupForm.password.focus();
    }
  });
}

// Optional: Login form can also have basic validation if needed
