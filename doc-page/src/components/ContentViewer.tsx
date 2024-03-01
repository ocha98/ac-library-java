import { Alert } from 'react-bootstrap'
import Markdown from './Markdown'
import { convertLanguageCodeToName } from '@/lib/utilClient'

export const ContentViewer = ({ content, lang }: { content: Content, lang: string }) => {
  let c = content.jp
  let notTranslated = false
  if (lang == 'en') {
    if (!content.en) notTranslated = true
    else c = content.en
  }

  return (
    <>
      {notTranslated && <Alert variant='warning'>{`This document has not yet been translated into ${convertLanguageCodeToName(lang)}.`}</Alert>}
      <Markdown content={c} />
    </>
  )
}