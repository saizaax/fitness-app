const registerButton = document.getElementById("register-button")
const passwordInput = document.getElementById("register-password-input")
const passwordRepeatInput = document.getElementById(
    "register-password-repeat-input"
)

const checkPassword = () => {
    if (passwordInput.value === passwordRepeatInput.value) {
        registerButton.style.opacity = 1
        registerButton.disabled = false
    }
    else {
        registerButton.style.opacity = 0.5
        registerButton.disabled = true
    }
}

passwordInput.addEventListener("change", checkPassword)
passwordRepeatInput.addEventListener("change", checkPassword)