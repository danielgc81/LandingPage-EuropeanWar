document.addEventListener ("scroll", () => {
   let maxHeight = window.document.body.scrollHeight - window.innerHeight;
   let percentage = ((window.scrollY) / maxHeight) * 100;
   document.querySelector(".scroll__line").style.width = percentage + '%';
});
