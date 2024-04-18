import './style.css'
import javascriptLogo from './javascript.svg'
import viteLogo from '/vite.svg'
import { setupCounter } from './counter.js'

// Tab selector logic

const title = document.getElementById("text-box-title")
const description = document.getElementById("text-box-description")

const tab1 = document.getElementById("tab1")
const tab2 = document.getElementById("tab2")
const tab3 = document.getElementById("tab3")

tab1.addEventListener("click", () => {
  title.innerHTML = "Overall Design"
  description.innerHTML = "Enter Overall Design Description Here"
})

tab2.addEventListener("click", () => {
  title.innerHTML = "Singleton"
  description.innerHTML = "Enter Singleton Description Here"
})

tab3.addEventListener("click", () => {
  title.innerHTML = "Strategy"
  description.innerHTML = "Enter Strategy Description Here"
})

// Image Selector

const back = document.getElementById("back-button")
const forward = document.getElementById("forward-button")

const img1 = document.getElementById("image-box-1")
const img2 = document.getElementById("image-box-2")
const img3 = document.getElementById("image-box-3")
const img4 = document.getElementById("image-box-4")
const img5 = document.getElementById("image-box-5")


let index = 0
const images = [img1, img2, img3, img4, img5]

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

})
