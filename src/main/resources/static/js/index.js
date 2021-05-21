const visits = document.getElementById("profile-visits")
const hours = document.getElementById("profile-hours")
const avgTime = document.getElementById("profile-avg-time")

const getAvgTime = () => {
    avgTime.innerHTML = `${Math.round(
        (parseInt(hours.innerHTML) * 60) / parseInt(visits.innerHTML)
    )} мин`
}
getAvgTime()

const subscriptionStart = document.getElementById("profile-subscription-start")
const subscriptionEnd = document.getElementById("profile-subscription-end")
const subscriptionDays = document.getElementById("profile-subscription-days")

const getSubscriptionDays = () => {
    const start = subscriptionStart.innerHTML.split(".")
    const startDate = new Date(+start[2], start[1] - 1, +start[0])

    const end = subscriptionEnd.innerHTML.split(".")
    const endDate = new Date(+end[2], end[1] - 1, +end[0])

    const days = Math.ceil(
        (endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24)
    )
    subscriptionDays.innerHTML = `${days} дней`
}
getSubscriptionDays()

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

const changeSubscriptionStatus = (event) => {
    const { target } = event

    const inactive = "program-action-btn"
    const active = "program-action-btn program-action-btn_active"
    const targetClass = target.className

    target.className = targetClass === inactive ? active : inactive
    target.innerHTML = targetClass === inactive ? "Отписаться" : "Записаться"
}

const addClickSubscriptionEvents = () => {
    const elements = Array.from(
        document.getElementsByClassName("program-action-btn")
    )

    elements.forEach((el) => (el.onclick = changeSubscriptionStatus))
}
addClickSubscriptionEvents()