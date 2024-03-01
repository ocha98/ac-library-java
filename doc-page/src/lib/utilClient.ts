import { notFound } from "next/navigation";
import { useRouter } from "next/router";

export const convertLanguageCodeToName = (code: string): string => {
  switch (code.toLowerCase()) {
    case 'en':
      return 'English';
    case 'jp':
      return 'Japanese';
    default:
      return 'Unknown';
  }
}

export const getLang = (): string => {
  const router = useRouter()
  const { lang } = router.query
  if (!lang) {
    notFound()
  }
  if (typeof lang !== 'string') {
    throw new Error('lang is not string. ' + typeof lang)
  }
  return lang
}