//
//  Course.swift
//  DetectionApp
//
//  Created by Nadine Osterkorn on 15.07.22.
//

import Foundation
import MessageUI
import SwiftUI

protocol CourseDelegate {
    func added(student: Student, to: Course)
    func removed(student: Student, from: Course)
}

class Course {
    var startDate: Date?
    var endDate: Date?
    var teacherName: String
    
    var delegate: CourseDelegate?
    
    var students = [Student]()
    
    init(startDate: Date?, endDate: Date?, teacherName: String, delegate: CourseDelegate) {
        self.startDate = startDate
        self.endDate = endDate
        self.teacherName = teacherName
        self.delegate = delegate
    }
    
    // MARK: Adding and removing students
    func add(student: Student) {
        if !student.name.isEmpty {
            students.append(student)
            sendEmailTo(student: student)
            
            // Inform delegate
            delegate!.added(student: student, to: self)
        }
    }
    
    func remove(student: Student) {
        var index: Int = -1
        for i in 0 ..< self.students.count {
            let obj = self.students[i]
            if obj.id == student.id {
                // Found the object with the correct studentID, store the index to delete it
                index = i
                break
            }
        }
        
        if index >= 0 {
            self.students.remove(at: index)
            self.delegate?.removed(student: student, from: self)
        }
    }
    
    // MARK: Helper
    private func sendEmailTo(student: Student) {
       // TODO: 2022-07-15 NO: Open mail composer
    }
}

class Student {
    var name: String
    var email: String
    var id: String
    var birthDate: Date
    
    var imageURL: URL?
    var avatar: UIImage?
    
    init(name: String, email: String, id: String, birthDate: Date) {
        self.name = name
        self.email = email
        self.id = id
        self.birthDate = birthDate
    }
    
    func studentAvatar() {
        ImageDownloader().download(imageURL: imageURL!) { image in
            self.avatar = image
        }
    }
}

class ImageDownloader {
    
    func download(imageURL: URL, completion: (_: UIImage) -> Void) {
        //... Imagine here a data task ansychronously downloading the image
        completion(UIImage())
    }
}
