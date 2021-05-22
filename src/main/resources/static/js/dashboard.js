const setUserInputs = (event) => {
    const target =
        event.target.className === "ri-pencil-fill"
            ? event.target.parentElement
            : event.target

    const { attributes } = target

    Array.from(attributes).forEach((el) => {
        const inputId = `${el.name}_input`

        if (inputId !== "class_input" && inputId !== "onclick_input") {
            const inputNode = document.getElementById(inputId)
            inputNode.value = el.value
        }
    })
}

const initUserInput = () => {
    const elements = document.getElementsByClassName("admin-profile_user-edit")

    if (elements.length > 0) elements[0].click()
}
initUserInput()

const getComplexityScale = () => {
    const elements = Array.from(
        document.getElementsByClassName("program-complexity-scale")
    )

    elements.forEach((el) => {
        const scale = el.getAttribute("data-scale")
        const children = Array.from(el.children)

        children.map((child, index) => {
            if (index < parseInt(scale))
                child.className =
                    "program-complexity-scale__item program-complexity-scale__item_active"
            return child
        })
    })
}
getComplexityScale()