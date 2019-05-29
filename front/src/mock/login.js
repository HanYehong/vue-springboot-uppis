import { getParams } from '@/libs/util'
const USER_MAP = {
  super_admin: {
    name: 'super_admin',
    user_id: '0',
    access: ['super_admin', 'admin','10001','10016','10014'],
    token: 'super_admin',
    avatar: 'https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png'
  },
  // admin: {
  //   name: 'admin',
  //   user_id: '2',
  //   access: ['admin'],
  //   token: 'admin',
  //   avatar: 'https://avatars0.githubusercontent.com/u/20942571?s=460&v=4'
  // },
  10001: {
    name: '10001',
    user_id: '1',
    access: ['10001'],
    token: '10001',
    avatar: 'https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png'
  },
  10016: {
    name: '10016',
    user_id: '2',
    access: ['10016'],
    token: '10016',
    avatar: 'https://avatars0.githubusercontent.com/u/20942571?s=460&v=4'
  },
  10014: {
    name: '10014',
    user_id: '3',
    access: ['10014'],
    token: '10014',
    avatar: 'https://avatars0.githubusercontent.com/u/20942571?s=460&v=4'
  },
  admin: {
    name: 'admin',
    user_id: '4',
    access: ['admin'],
    token: 'admin',
    avatar: 'https://avatars0.githubusercontent.com/u/20942571?s=460&v=4'
  }
}

export const login = req => {
  req = JSON.parse(req.body)
  return { token: USER_MAP[req.userName].token }
}

export const getUserInfo = req => {
  const params = getParams(req.url)
  return USER_MAP[params.token]
}

export const logout = req => {
  return null
}
