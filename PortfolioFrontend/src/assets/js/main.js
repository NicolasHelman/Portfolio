
(function() {
    "use strict";
  
    /**
     * Función de ayuda al selector fácil
     */
    const select = (el, all = false) => {
      el = el.trim()
      if (all) {
        return [...document.querySelectorAll(el)]
      } else {
        return document.querySelector(el)
      }
    }
  
    /**
     * Función de escucha de eventos fácil
     */
    const on = (type, el, listener, all = false) => {
      let selectEl = select(el, all)
      if (selectEl) {
        if (all) {
          selectEl.forEach(e => e.addEventListener(type, listener))
        } else {
          selectEl.addEventListener(type, listener)
        }
      }
    }
  
    /**
     * Funcion de escucha de eventos facil en el scroll 
     */
    const onscroll = (el, listener) => {
      el.addEventListener('scroll', listener)
    }
  
    /**
     * Estado activo de los enlaces de la barra de navegación al desplazarse
     */
    let navbarlinks = select('#navbar .scrollto', true)
    const navbarlinksActive = () => {
      let position = window.scrollY + 200
      navbarlinks.forEach(navbarlink => {
        if (!navbarlink.hash) return
        let section = select(navbarlink.hash)
        if (!section) return
        if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
          navbarlink.classList.add('active')
        } else {
          navbarlink.classList.remove('active')
        }
      })
    }
    window.addEventListener('load', navbarlinksActive)
    onscroll(document, navbarlinksActive)
  
  
    /**
     * Botón de vuelta al principio
     */
    let backtotop = select('.back-to-top')
    if (backtotop) {
      const toggleBacktotop = () => {
        if (window.scrollY > 100) {
          backtotop.classList.add('active')
        } else {
          backtotop.classList.remove('active')
        }
      }
      window.addEventListener('load', toggleBacktotop)
      onscroll(document, toggleBacktotop)
    }
  
    /**
     * Activación de la navegación móvil
     */
    on('click', '.mobile-nav-toggle', function(e) {
      select('body').classList.toggle('mobile-nav-active')
      this.classList.toggle('fa-mouse')
      this.classList.toggle('fa-times-circle')
    })

        /**
     * Se desplaza a un elemento 'a' con el id. Por ejemplo, #experiencia
     */
         const scrollto = (el) => {
          let elementPos = select(el).offsetTop
          window.scrollTo({
            top: elementPos,
            behavior: 'smooth'
          })
        }
  
    /**
     * Scroll con desplazamiento en los enlaces con nombre de clase .scrollto
     */
    on('click', '.scrollto', function(e) {
      if (select(this.hash)) {
        e.preventDefault()
  
        let body = select('body')
        if (body.classList.contains('mobile-nav-active')) {
          body.classList.remove('mobile-nav-active')
          let navbarToggle = select('.mobile-nav-toggle')
          navbarToggle.classList.toggle('fa-mouse')
          navbarToggle.classList.toggle('fa-times-circle')
        }
        scrollto(this.hash)
      }
    }, true)
  
    /**
     * Scroll con desplazamiento en la carga de la página con enlaces hash en la url
     */
    window.addEventListener('load', () => {
      if (window.location.hash) {
        if (select(window.location.hash)) {
          scrollto(window.location.hash)
        }
      }
    });

      /**
     * Efecto tipado en Persona
     */
    const typed = select('.typed')
    if (typed) {
      let typed_strings = typed.getAttribute('data-typed-items')
      typed_strings = typed_strings?.split(',') // operador de encadenamiento opcional (?.), que cortocircuita devolviendo sin definir si el valor a la izquierda es 'null' o 'undefined'
      new Typed('.typed', {
        strings: typed_strings,
        loop: true, // Repetir el array de strings
        typeSpeed: 100, // Velocidad en mlisegundos para poner una letra
        backSpeed: 50, // Velocidad en milisegundos para borrrar una letra
        backDelay: 2000 // Tiempo de espera despues de que termina de escribir una palabra
      });
    }

        /**
     * Animacion en las habilidades
     */
    let skilsContent = select('.habilidades-content');
    function animacionSkill(){
      if (skilsContent) {
          let progress = select('.progress .progress-bar', true);
          progress.forEach((el) => {
            el.style.width = el.getAttribute('aria-valuenow') + '%'
          }); 
      };
    }
    window.addEventListener('scroll',animacionSkill);
    

    /**
     * Animación en el desplazamiento
     */
    let animado = document.querySelectorAll(".animacion"); //selecciono todos los elementos que tengan la clase animado

    //Detecto la cantidad de scroll y por consecuencia haré que aparezcan los elementos
  
    //@scrollTop
    //  es la cantidad de scroll que voy haciendo a medida que voy bajando
  
    //Luego hago un for porque la clase animado tiene varios elementos
  
    //offsetTop
    //  detecta la altura que hay desde el inicio de la ventana hasta donde está el elemento
  
    //@alturaAnimado
    //  guardo el resultado de offsetTop
  
    function mostrarScroll() {
      let scrollTop = document.documentElement.scrollTop;
      for (var i=0; i < animado.length; i++) {
        let alturaAnimado = animado[i].offsetTop;   //detecto la altura de cada elemento 
        if (alturaAnimado -550 < scrollTop) {
          animado[i].style.opacity = 1;
          animado[i].classList.add("mostrarArriba");
          animado[i].classList.add("mostrarDerecha");
        }
      }
    }
    //Cuando escuche el elemento scroll, se va a ejecutar la función mostrarScroll
    window.addEventListener('scroll',mostrarScroll);

    // --- PAGE LOADER ---
    window.onload = () => {
      let loader = document.querySelector('.loader');
      let container = document.querySelector('.cont');
      setTimeout(function(){
        container.style.visibility = 'hidden';
        container.style.opacity = '0';
        loader.style.visibility = 'hidden';
        loader.style.opacity = '0';
      }, 1200);
    } 


})()