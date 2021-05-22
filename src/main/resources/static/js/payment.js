const daysInput = document.getElementById("payment-days-input")
const cardNumberInput = document.getElementById("payment-card-number-input")
const cardCvcInput = document.getElementById("payment-card-cvc-input")
const cardDateInput = document.getElementById("payment-card-date-input")

const paymentButton = document.getElementById("payment-btn")
const priceOutput = document.getElementById("payment-price")

const validateData = () => {
    if (daysInput.value > 0 && cardNumberInput.value.length >= 12 && cardCvcInput.value.length == 3 && cardDateInput.value.length >= 5) {
        paymentButton.style.opacity = 1
        paymentButton.disabled = false
    }
    else {
        paymentButton.style.opacity = 0.5
        paymentButton.disabled = true
    }
}
validateData()

const calculatePrice = () => {
    const { value } = daysInput

    if (parseInt(value) > 0) {
        const start = priceOutput.getAttribute("data-subscription-start").split(".")
        const subDate = new Date(+start[2], start[1] - 1, +start[0])
        subDate.setDate(subDate.getDate() + parseInt(value));
        priceOutput.setAttribute("data-subscription-end", subDate.toLocaleDateString())
        priceOutput.innerHTML = `${parseInt(value) * 99} руб`
    }
}

daysInput.addEventListener("change", validateData)
daysInput.addEventListener("change", calculatePrice)

cardNumberInput.addEventListener("change", validateData)
cardCvcInput.addEventListener("change", validateData)
cardDateInput.addEventListener("change", validateData)