package com.example.storeRental.service

import com.example.storeRental.domain.UserModel
import com.example.storeRental.repo.UserRepo
import com.example.storeRental.utils.requestClass.UserLoginRequest
import com.example.storeRental.utils.requestClass.UserRegisterRequest
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepo: UserRepo):BaseSevice<UserModel, Long>{

    override fun save(model: UserModel) {
        if(model != null)
            userRepo.save(model)
    }

    override fun getById(id: Long): UserModel {
        return userRepo.findById(id).orElse(null)
    }

    override fun deleteById(id: Long) {
        val user = userRepo.findById(id).orElse(null)
        if(user != null){
            userRepo.delete(user)
        }
    }

    fun getAllUser():Iterable<UserModel>{
        return userRepo.findAll()
    }

    fun register(userRes: UserRegisterRequest): Boolean{

        if(userRes.username.length > 5 &&
            userRes.password.length > 8 &&
             userRes.email != null
        ){
            val newUser = UserModel(username = userRes.username, email = userRes.email)
            newUser.setPassword(userRes.password)
            userRepo.save(newUser)
            return true
        }
        return false
    }

    fun login(userRes: UserLoginRequest):Boolean{
        if(userRes.password.length > 8 && userRes.email != null )
        {
            var user = userRepo.findByEmail(userRes.email).orElse(null)
            if(BCrypt.checkpw(userRes.password, user.getHashPwd()))
                return true
            return false
        }
        return false
    }

}