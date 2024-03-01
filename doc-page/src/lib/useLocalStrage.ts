import { useCallback, useEffect, useState } from 'react';

export const useLocalStorage = (key: string, initValue: string): [string, (value: string) => void] => {
  const [value, setValue] = useState(initValue)

  const setLocalStorageValue = useCallback(
    (newValue: string) => {
      setValue(newValue)
      window.localStorage.setItem(key, newValue)
      localStorage.setItem(key, newValue)
    },
    [key]
  )

  useEffect(() => {
    const value = window.localStorage.getItem(key)
    if (value) setValue(value)
  }, [])


  return [value, setLocalStorageValue] as const
}