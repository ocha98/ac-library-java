import fs from 'fs';
import path from 'path';

const BASE_DIR = '../doc'

export const LANGS = ['en', 'jp'] as const

export const getCategories = (): string[] => {
  return fs.readdirSync(path.join(BASE_DIR, 'jp'), { withFileTypes: true })
    .filter((dirent) => dirent.isDirectory())
    .map((categoryDir) => categoryDir.name)
}

export const getSlugsByCategory = (category: string): string[] => {
  const categoryPath = `${BASE_DIR}/jp/${category}`;
  const categoryFiles = fs.readdirSync(categoryPath)
  return categoryFiles
    .filter((fileName) => fileName.endsWith('.md'))
    .map((fileName) => fileName.replace(/\.md$/, ''))
}

export const getContentPath = (p: string): Content => {
  const enPath = path.join(BASE_DIR, 'en', p);
  const jpPath = path.join(BASE_DIR, 'jp', p);
  let en = null;
  if(fs.existsSync(enPath)) {
    en = fs.readFileSync(enPath, 'utf-8')
  }
  return {
    jp: fs.readFileSync(jpPath, 'utf-8'),
    en
  }
}

export const getContent = (category: string, slug: string): Content => {
  return getContentPath(path.join(category, `${slug}.md`))
}
