import i18n from "i18next";
import {  initReactI18next } from "react-i18next";
import en from "./translations/en.json"
import tr from "./translations/tr.json"


const initialLanguage = localStorage.getItem('lang') || navigator.language || en

export const i18nInstance =  i18n.use(initReactI18next)

i18nInstance.init({
    resources: {
      en: {
       
           translation: en
        
      },
      tr:{
        translation: tr
      }
    },
     //fallbackLng: navigator.language,
   fallbackLng: initialLanguage, //bunu yapınca ingilizceye gecmeme sorunu oluyor
    //fallbackLng: "tr",
    //fallbackLng: "en", eğer bilinmeyen bir dil ise onu otomatik çevireçeğim dil

    interpolation: {
      escapeValue: false 
    }
  });