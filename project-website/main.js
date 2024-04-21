import './style.css'

import { description1, description2, description3, description4, description5, description6, description7 } from './descriptions'

import javascriptLogo from './javascript.svg'
import viteLogo from '/vite.svg'
import { setupCounter } from './counter.js'

// Tab selector logic

const title = document.getElementById("text-box-title")
const description = document.getElementById("text-box-description")
const img = document.getElementById("text-box-image")

const tab1 = document.getElementById("tab1")
const tab2 = document.getElementById("tab2")
const tab3 = document.getElementById("tab3")
const tab4 = document.getElementById("tab4")
const tab5 = document.getElementById("tab5")
const tab6 = document.getElementById("tab6")
const tab7 = document.getElementById("tab7")

tab1.addEventListener("click", () => {
  title.innerHTML = "DCD Diagram"
  description.innerHTML = description1
  img.src = "img/DCD.png"
})

tab2.addEventListener("click", () => {
  title.innerHTML = "Sequence Diagram"
  description.innerHTML = description2
  img.src = "img/sequence.png"
})

tab3.addEventListener("click", () => {
  title.innerHTML = "Single Responsibility"
  description.innerHTML = description3
  img.src = "img/single.png"
})

tab4.addEventListener("click", () => {
  title.innerHTML = "Interface Segregation Principle"
  description.innerHTML = description4
  img.src = "img/segregation.png"
})

tab5.addEventListener("click", () => {
  title.innerHTML = "Open-Closed Principle"
  description.innerHTML = description5
  img.src = "img/openclosed.png"
})

tab6.addEventListener("click", () => {
  title.innerHTML = "Strategy"
  description.innerHTML = description6
  img.src = "img/strategy.png"
})

tab7.addEventListener("click", () => {
  title.innerHTML = "Singleton"
  description.innerHTML = description7
  img.src = "img/singleton.png"
})

// Image Selector

const back = document.getElementById("back-button")
const forward = document.getElementById("forward-button")

const img1 = document.getElementById("image-box-1")
const img2 = document.getElementById("image-box-2")
const img3 = document.getElementById("image-box-3")
const img4 = document.getElementById("image-box-4")
const img5 = document.getElementById("image-box-5")
const img6 = document.getElementById("image-box-6")
const img7 = document.getElementById("image-box-7")

const caption = document.getElementById("caption")

const caption1 = "Once the app has loaded, it brings the user to a convenient log-in screen, which allows the user to log in with a secure email and password or create a new account if needed."
const caption2 = "After the user successfully logs in, they are brought to the “Input Meal” screen. This screen allows the user to input their meals for the day, which updates the “Total Calories” count below. This screen also displays the user’s personal information, which can be updated in the Update Personal Info tab. Below that, the user’s total calories for the day and calculated goal (based on their personal information) is shown for the user to track their calories effectively. At the bottom of the screen, the user is able to toggle between 2 different visualizations of their calorie counts."
const caption3 = "Using the navigation bar at the bottom, the user can navigate to the Recipe screen. In this screen, the user can add a Recipe and the Ingredients/Quantities needed to make it. These recipes are saved into a global database that can be viewed by any user. At the bottom of the screen, we see a list of the recipes available. Next to each recipe, the first button is able to open a list of ingredients if the user has the ingredients needed to make the recipe. If the user does not have enough ingredients, they can press the button on the right to add the ingredients to their shopping list so they can buy them."
const caption4 = "When we open the ingredients screen, we see the recipe name along with the necessary ingredients and their quantities. At the bottom of the screen, we can go back to the Recipe Screen or we can “Cook” the recipe. This will input the meal into the User’s day and take out the ingredients used from the user’s pantry."
const caption5 = "The next screen is the Ingredients Screen. Here we can input ingredients that the user has in their pantry, along with the quantity, calories, and expiration date. On the bottom half of the screen, the app presents a scrollable list of each ingredient and its quantity. Each ingredient also has a “-” and “+” which allow the user to manually increment or decrement the amount of an ingredient available accordingly."
const caption6 = "The next screen is the Shopping List screen. In this screen, users can add items to a shopping list. Using the “Add to Cart” and “Checkout” buttons, the user can buy specified ingredients from the shopping list, which makes them appear in their Pantry."
const caption7 = "The last screen, as mentioned previously, is the Update Personal Information screen. This screen allows the user to input / update their personal information whenever needed to accommodate fluctuations in their health."

let index = 0
const images = [img1, img2, img3, img4, img5, img6, img7]
const captions = [caption1, caption2, caption3, caption4, caption5, caption6, caption7]


back.addEventListener("click", () => {
  
  index = (index - 1) % images.length

  if (index == -1) {
    index = 4
  }

  for(let i = 0; i < images.length; i++) {

    if (i == index) {
      images[i].setAttribute("class", "duration-700 ease-in-out")
    } else {
      images[i].setAttribute("class", "hidden duration-700 ease-in-out")
    }

    caption.innerHTML = captions[index]

  }

})

forward.addEventListener("click", () => {

  index = (index + 1) % images.length

  for(let i = 0; i < images.length; i++) {
    if (i == index) {
      images[i].setAttribute("class", "duration-700 ease-in-out")
    } else {
      images[i].setAttribute("class", "hidden duration-700 ease-in-out")
    }
  }
  caption.innerHTML = captions[index]

})
